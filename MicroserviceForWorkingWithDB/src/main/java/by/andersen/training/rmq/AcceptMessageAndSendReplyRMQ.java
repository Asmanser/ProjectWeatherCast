package by.andersen.training.rmq;

import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class AcceptMessageAndSendReplyRMQ extends Thread {

    private ConnectionFactory factory;

    private String RPC_Queue_Name;

    private ParserJsonAndAnswerRMQ parserJsonAndAnswerRMQ;

    public AcceptMessageAndSendReplyRMQ(ConnectionFactory factory, String RPC_Queue_Name, ParserJsonAndAnswerRMQ parserJsonAndAnswerRMQ) {
        this.factory = factory;
        this.RPC_Queue_Name = RPC_Queue_Name;
        this.parserJsonAndAnswerRMQ = parserJsonAndAnswerRMQ;
    }

    public void run() {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(RPC_Queue_Name, false, false, false, null);
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
                    response = parserJsonAndAnswerRMQ.parseAndAnswer(message);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    response = "false";
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

    public ParserJsonAndAnswerRMQ getParserJsonAndAnswerRMQ() {
        return parserJsonAndAnswerRMQ;
    }

}
