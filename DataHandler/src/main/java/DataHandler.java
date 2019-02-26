import by.andersen.training.models.Accessory;
import by.andersen.training.models.Cap;
import by.andersen.training.models.FootWear;
import by.andersen.training.models.OuterWear;
import by.andersen.training.models.UnderWear;
import by.andersen.training.models.rabbitmq.AccessoryRMQ;
import by.andersen.training.models.rabbitmq.CapRMQ;
import by.andersen.training.models.rabbitmq.FootWearRMQ;
import by.andersen.training.models.rabbitmq.OuterWearRMQ;
import by.andersen.training.models.rabbitmq.UnderWearRMQ;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

public class DataHandler implements AutoCloseable{

    private ConnectionFactory connectionFactory;

    private Connection connection;

    private Channel channel;

    private List<OuterWear> outerWears;

    private List<Cap> caps;

    private List<UnderWear> underWears;

    private List<FootWear> footWears;

    private List<Accessory> accessories;

    public DataHandler() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public static void main(String[] args) {
        try(DataHandler dataHandler = new DataHandler()) {
            Gson gson = new Gson();

            CapRMQ capRMQ = new CapRMQ("GETALLWITHLAZY", new Cap());
            String jsonCaps = dataHandler.call(gson.toJson(capRMQ), "cap");
            Type jsonTypeCaps = new TypeToken<ArrayList<Cap>>(){}.getType();
            dataHandler.setCaps(gson.fromJson(jsonCaps, jsonTypeCaps));

            OuterWearRMQ outerWearRMQ = new OuterWearRMQ("GETALLWITHLAZY", new OuterWear());
            String jsonOuterWear = dataHandler.call(gson.toJson(outerWearRMQ), "outerWear");
            Type jsonTypeOuterWear = new TypeToken<ArrayList<OuterWear>>(){}.getType();
            dataHandler.setOuterWears(gson.fromJson(jsonOuterWear, jsonTypeOuterWear));

            UnderWearRMQ underWearRMQ = new UnderWearRMQ("GETALLWITHLAZY", new UnderWear());
            String jsonUnderWear = dataHandler.call(gson.toJson(underWearRMQ), "underWear");
            Type jsonTypeUnderWear = new TypeToken<ArrayList<UnderWear>>(){}.getType();
            dataHandler.setUnderWears(gson.fromJson(jsonUnderWear, jsonTypeUnderWear));

            FootWearRMQ footWearRMQ = new FootWearRMQ("GETALLWITHLAZY", new FootWear());
            String jsonFootWear = dataHandler.call(gson.toJson(footWearRMQ), "footWear");
            Type jsonTypeFootWear = new TypeToken<ArrayList<FootWear>>(){}.getType();
            dataHandler.setFootWears(gson.fromJson(jsonFootWear, jsonTypeFootWear));

            AccessoryRMQ accessoryRMQ = new AccessoryRMQ("GETALLWITHLAZY", new Accessory());
            String jsonAccessory = dataHandler.call(gson.toJson(accessoryRMQ), "accessory");
            Type jsonTypeAccessory = new TypeToken<ArrayList<Accessory>>(){}.getType();
            dataHandler.setAccessories(gson.fromJson(jsonAccessory, jsonTypeAccessory));

            ServerRMQ serverRMQ = new ServerRMQ(dataHandler.outerWears,dataHandler.caps,dataHandler.underWears,dataHandler.footWears,dataHandler.accessories);
            serverRMQ.open();

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //
    public String call(String message, String requestQueueName) throws IOException, InterruptedException {
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

    @Override
    public void close() throws Exception {
        connection.close();
    }


    public List<OuterWear> getOuterWears() {
        return outerWears;
    }

    public void setOuterWears(List<OuterWear> outerWears) {
        this.outerWears = outerWears;
    }

    public List<Cap> getCaps() {
        return caps;
    }

    public void setCaps(List<Cap> caps) {
        this.caps = caps;
    }

    public List<UnderWear> getUnderWears() {
        return underWears;
    }

    public void setUnderWears(List<UnderWear> underWears) {
        this.underWears = underWears;
    }

    public List<FootWear> getFootWears() {
        return footWears;
    }

    public void setFootWears(List<FootWear> footWears) {
        this.footWears = footWears;
    }

    public List<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Accessory> accessories) {
        this.accessories = accessories;
    }
}
