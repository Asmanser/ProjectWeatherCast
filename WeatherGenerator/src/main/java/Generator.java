import by.andersen.training.weathercast.models.City;
import by.andersen.training.weathercast.models.DirectionWind;
import by.andersen.training.weathercast.models.Overcast;
import by.andersen.training.weathercast.models.WeatherCondition;
import by.andersen.training.weathercast.models.WeatherInformation;
import by.andersen.training.weathercast.models.rabbitmq.CityRMQ;
import by.andersen.training.weathercast.models.rabbitmq.DirectionWindRMQ;
import by.andersen.training.weathercast.models.rabbitmq.OvercastRMQ;
import by.andersen.training.weathercast.models.rabbitmq.WeatherConditionRMQ;
import by.andersen.training.weathercast.models.rabbitmq.WeatherInformationRMQ;
import by.andersen.training.weathercast.rmq.SendMessageAndAcceptResponseRMQ;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Generator implements AutoCloseable{

    private ConnectionFactory connectionFactory;

    private List<City> cities;

    private List<WeatherCondition> weatherConditions;

    private List<Overcast> overcasts;

    private List<DirectionWind> directionWinds;

    public Generator() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        this.connectionFactory = factory;
    }

    public static void main(String[] argv) throws InterruptedException {
        try(Generator generator = new Generator()) {
            Gson gson = new Gson();

            SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQCity =
                    new SendMessageAndAcceptResponseRMQ(generator.connectionFactory,
                            "city",gson.toJson(new CityRMQ("GETALLWITHLAZY", new City())));
            sendMessageAndAcceptResponseRMQCity.start();
            sendMessageAndAcceptResponseRMQCity.join();
            Type jsonTypeCities = new TypeToken<ArrayList<City>>(){}.getType();
            generator.setCities(gson.fromJson(sendMessageAndAcceptResponseRMQCity.getAnswer(), jsonTypeCities));

            SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQDirectionWind =
                    new SendMessageAndAcceptResponseRMQ(generator.connectionFactory,
                            "directionWind",gson.toJson(new DirectionWindRMQ("GETALL", new DirectionWind())));
            sendMessageAndAcceptResponseRMQDirectionWind.start();
            sendMessageAndAcceptResponseRMQDirectionWind.join();
            Type jsonTypeDirectionWind = new TypeToken<ArrayList<DirectionWind>>(){}.getType();
            generator.setDirectionWinds(gson.fromJson(sendMessageAndAcceptResponseRMQDirectionWind.getAnswer(), jsonTypeDirectionWind));

            SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQWeatherCondition =
                    new SendMessageAndAcceptResponseRMQ(generator.connectionFactory,
                            "weatherCondition",gson.toJson(new WeatherConditionRMQ("GETALLWITHLAZY",new WeatherCondition())));
            sendMessageAndAcceptResponseRMQWeatherCondition.start();
            sendMessageAndAcceptResponseRMQWeatherCondition.join();
            Type jsonTypeWeatherCondition = new TypeToken<ArrayList<WeatherCondition>>(){}.getType();
            generator.setWeatherConditions(gson.fromJson(sendMessageAndAcceptResponseRMQWeatherCondition.getAnswer(), jsonTypeWeatherCondition));

            SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQOvercast =
                    new SendMessageAndAcceptResponseRMQ(generator.connectionFactory,
                            "overcast",gson.toJson(new OvercastRMQ("GETALLWITHLAZY", new Overcast())));
            sendMessageAndAcceptResponseRMQOvercast.start();
            sendMessageAndAcceptResponseRMQOvercast.join();
            Type jsonTypeOvercast = new TypeToken<ArrayList<Overcast>>(){}.getType();
            generator.setOvercasts(gson.fromJson(sendMessageAndAcceptResponseRMQOvercast.getAnswer(), jsonTypeOvercast));

            generator.generate();

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generate() throws InterruptedException, IOException {
        LocalDate localDate = LocalDate.now();
        Gson gson = new Gson();
        while(true) {
            for(City city : this.cities) {
                WeatherInformation weatherInformation = new WeatherInformation();
                weatherInformation.setMinAirTemperature((int)(Math.random() * ((50 - (-20)) + 1)));
                weatherInformation.setMaxAirTemperature(weatherInformation.getMinAirTemperature() + 2);
                weatherInformation.setWindSpeed((int)(Math.random() * ((10 - 0) + 1)));
                weatherInformation.setAtmospherePressure((int)(Math.random() * ((770 - 750) + 1)));
                weatherInformation.setAirHumidity((int)(Math.random() * ((100 - 0) + 1)));
                weatherInformation.setDate(Date.valueOf(localDate.toString()));
                weatherInformation.setCity(city);
                weatherInformation.setDirectionWind(this.directionWinds.get((int)(Math.random() * ((this.directionWinds.size()-1) - 0) + 1)));
                weatherInformation.setOvercast(this.overcasts.get((int)(Math.random() * ((this.overcasts.size()-1) - 0) + 1)));
                weatherInformation.setWeatherCondition(this.weatherConditions.get((int)(Math.random() * ((this.weatherConditions.size()-1) - 0) + 1)));
                weatherInformation.setWeatherClothing(null);
                WeatherInformationRMQ weatherInformationRMQ = new WeatherInformationRMQ("SAVE",weatherInformation);
                SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQWeatherInformation =
                        new SendMessageAndAcceptResponseRMQ(this.connectionFactory,
                                "weatherInformationHandler",gson.toJson(weatherInformationRMQ));
                sendMessageAndAcceptResponseRMQWeatherInformation.start();
                sendMessageAndAcceptResponseRMQWeatherInformation.join();
                String jsonWeatherInformation = sendMessageAndAcceptResponseRMQWeatherInformation.getAnswer();
                if(!jsonWeatherInformation.equals("true")) {
                    System.out.println("Don't save: " + gson.toJson(weatherInformationRMQ));
                }
            }
            Thread.sleep(15000);
            localDate = localDate.plusDays(1);
        }
    }

    @Override
    public void close() throws Exception {
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<WeatherCondition> getWeatherConditions() {
        return weatherConditions;
    }

    public void setWeatherConditions(List<WeatherCondition> weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    public List<Overcast> getOvercasts() {
        return overcasts;
    }

    public void setOvercasts(List<Overcast> overcasts) {
        this.overcasts = overcasts;
    }

    public List<DirectionWind> getDirectionWinds() {
        return directionWinds;
    }

    public void setDirectionWinds(List<DirectionWind> directionWinds) {
        this.directionWinds = directionWinds;
    }

}
