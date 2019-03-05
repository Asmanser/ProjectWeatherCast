package by.andersen.training.weathercast.rmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

public class SendMessageAndAcceptResponseRMQ extends Thread {

    private ConnectionFactory factory;

    private String RPC_Queue_Name;

    private String message;

    private String answer;

    public SendMessageAndAcceptResponseRMQ(ConnectionFactory factory, String RPC_Queue_Name, String message) {
        this.factory = factory;
        this.RPC_Queue_Name = RPC_Queue_Name;
        this.message = message;
    }

    public SendMessageAndAcceptResponseRMQ(ConnectionFactory factory, String RPC_Queue_Name) {
        this.factory = factory;
        this.RPC_Queue_Name = RPC_Queue_Name;
    }

    public SendMessageAndAcceptResponseRMQ(ConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void run() {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            answer = call(message, RPC_Queue_Name, channel);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public String call(String message, String requestQueueName, Channel channel) throws IOException, InterruptedException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));

        final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response.offer(new String(delivery.getBody(), "UTF-8"));
            }
        }, consumerTag -> {
        });

        String result = response.take();
        channel.basicCancel(ctag);
        return result;
    }


    public String getAnswer() {
        return answer;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRPC_Queue_Name(String RPC_Queue_Name) {
        this.RPC_Queue_Name = RPC_Queue_Name;
    }
}
