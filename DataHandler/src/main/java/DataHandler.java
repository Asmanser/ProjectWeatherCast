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
import by.andersen.training.rmq.AcceptMessageAndSendReplyRMQ;
import by.andersen.training.rmq.SendMessageAndAcceptResponseRMQ;
import by.andersen.training.rmq.WeatherInformationParserJsonAndAnswerRMQ;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class DataHandler implements AutoCloseable{

    private ConnectionFactory connectionFactory;

    private List<OuterWear> outerWears;

    private List<Cap> caps;

    private List<UnderWear> underWears;

    private List<FootWear> footWears;

    private List<Accessory> accessories;

    public DataHandler() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connectionFactory = factory;
    }

    public static void main(String[] args) {
        try(DataHandler dataHandler = new DataHandler()) {
            Gson gson = new Gson();

            SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQCap =
                    new SendMessageAndAcceptResponseRMQ(dataHandler.connectionFactory,
                    "cap",gson.toJson(new CapRMQ("GETALLWITHLAZY", new Cap())));
            sendMessageAndAcceptResponseRMQCap.start();
            sendMessageAndAcceptResponseRMQCap.join();
            Type jsonTypeCaps = new TypeToken<ArrayList<Cap>>(){}.getType();
            dataHandler.setCaps(gson.fromJson(sendMessageAndAcceptResponseRMQCap.getAnswer(), jsonTypeCaps));

            SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQOuterWear =
                    new SendMessageAndAcceptResponseRMQ(dataHandler.connectionFactory,
                    "outerWear",gson.toJson(new OuterWearRMQ("GETALLWITHLAZY", new OuterWear())));
            sendMessageAndAcceptResponseRMQOuterWear.start();
            sendMessageAndAcceptResponseRMQOuterWear.join();
            Type jsonTypeOuterWear = new TypeToken<ArrayList<OuterWear>>(){}.getType();
            dataHandler.setOuterWears(gson.fromJson(sendMessageAndAcceptResponseRMQOuterWear.getAnswer(), jsonTypeOuterWear));

            SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQUnderWear =
                    new SendMessageAndAcceptResponseRMQ(dataHandler.connectionFactory,
                    "underWear",gson.toJson(new UnderWearRMQ("GETALLWITHLAZY", new UnderWear())));
            sendMessageAndAcceptResponseRMQUnderWear.start();
            sendMessageAndAcceptResponseRMQUnderWear.join();
            Type jsonTypeUnderWear = new TypeToken<ArrayList<UnderWear>>(){}.getType();
            dataHandler.setUnderWears(gson.fromJson(sendMessageAndAcceptResponseRMQUnderWear.getAnswer(), jsonTypeUnderWear));

            SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQFootWear =
                    new SendMessageAndAcceptResponseRMQ(dataHandler.connectionFactory,
                    "footWear",gson.toJson(new FootWearRMQ("GETALLWITHLAZY", new FootWear())));
            sendMessageAndAcceptResponseRMQFootWear.start();
            sendMessageAndAcceptResponseRMQFootWear.join();
            Type jsonTypeFootWear = new TypeToken<ArrayList<FootWear>>(){}.getType();
            dataHandler.setFootWears(gson.fromJson(sendMessageAndAcceptResponseRMQFootWear.getAnswer(), jsonTypeFootWear));

            SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQAccessory =
                    new SendMessageAndAcceptResponseRMQ(dataHandler.connectionFactory,
                    "accessory",gson.toJson(new AccessoryRMQ("GETALLWITHLAZY", new Accessory())));
            sendMessageAndAcceptResponseRMQAccessory.start();
            sendMessageAndAcceptResponseRMQAccessory.join();
            Type jsonTypeAccessory = new TypeToken<ArrayList<Accessory>>(){}.getType();
            dataHandler.setAccessories(gson.fromJson(sendMessageAndAcceptResponseRMQAccessory.getAnswer(), jsonTypeAccessory));


            AcceptMessageAndSendReplyRMQ acceptMessageAndSendReplyRMQ = new AcceptMessageAndSendReplyRMQ(dataHandler.connectionFactory,
                    "weatherInformationHandler", new WeatherInformationParserJsonAndAnswerRMQ(
                    dataHandler.connectionFactory,"weatherInformation",dataHandler.outerWears, dataHandler.caps,
                    dataHandler.underWears, dataHandler.footWears,dataHandler.accessories)
            );
            acceptMessageAndSendReplyRMQ.start();

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
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
