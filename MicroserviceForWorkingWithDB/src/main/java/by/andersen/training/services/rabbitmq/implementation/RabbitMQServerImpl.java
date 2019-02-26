package by.andersen.training.services.rabbitmq.implementation;

import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
import by.andersen.training.services.rabbitmq.interfaces.RabbitMQServer;
import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.swing.text.html.parser.Parser;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class RabbitMQServerImpl implements RabbitMQServer {

    private ConnectionFactory connectionFactory = new ConnectionFactory();

    @Autowired
    private UserParserJsonAndAnswerRMQ userParserJsonAndAnswerRMQ;

    @PostConstruct
    private void init() {
        connectionFactory.setHost("localhost");
    }

    @Override
    public void open() {
        Runner runner = new Runner(connectionFactory,"user",userParserJsonAndAnswerRMQ );
        Thread thread = new Thread(runner);
        thread.start();
    }

    private class Runner implements Runnable {

        private ConnectionFactory factory;

        private String RPC_Queue_Name = "";
        private ParserJsonAndAnswerRMQ parserJsonAndAnswerRMQ;

        public Runner(ConnectionFactory factory, String RPC_Queue_Name, ParserJsonAndAnswerRMQ parserJsonAndAnswerRMQ) {
            this.factory = factory;
            this.RPC_Queue_Name = RPC_Queue_Name;
            this.parserJsonAndAnswerRMQ = parserJsonAndAnswerRMQ;
        }

        public void run() {
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {
                channel.queueDeclare(RPC_Queue_Name, false, false, false, null);
                //channel.queuePurge(RPC_Queue_Name);
                channel.basicQos(1);
                Object monitor = new Object();
                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                            .Builder()
                            .correlationId(delivery.getProperties().getCorrelationId())
                            .build();

                    String response = "";

                    try {
                        String message = new String(delivery.getBody(), "UTF-8");
                        response += parserJsonAndAnswerRMQ.parseAndAnswer(message);
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    } finally {
                        channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.getBytes("UTF-8"));
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                        // RabbitMq consumer worker thread notifies the RPC server owner thread
                        synchronized (monitor) {
                            monitor.notify();
                        }
                    }
                };

                channel.basicConsume(RPC_Queue_Name, false, deliverCallback, (consumerTag -> { }));
                // Wait and be prepared to consume the message from RPC client.
                while (true) {
                    synchronized (monitor) {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
