import by.andersen.training.models.City;
import by.andersen.training.models.DirectionWind;
import by.andersen.training.models.Overcast;
import by.andersen.training.models.WeatherCondition;
import by.andersen.training.models.WeatherInformation;
import by.andersen.training.models.rabbitmq.CityRMQ;
import by.andersen.training.models.rabbitmq.DirectionWindRMQ;
import by.andersen.training.models.rabbitmq.OvercastRMQ;
import by.andersen.training.models.rabbitmq.WeatherConditionRMQ;
import by.andersen.training.models.rabbitmq.WeatherInformationRMQ;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sun.javafx.cursor.CursorType;
import java.time.temporal.ChronoField;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

public class Generator implements AutoCloseable{

    private ConnectionFactory connectionFactory;

    private Connection connection;

    private Channel channel;

    private List<City> cities;

    private List<WeatherCondition> weatherConditions;

    private List<Overcast> overcasts;

    private List<DirectionWind> directionWinds;

    public Generator() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public static void main(String[] argv) throws InterruptedException {
        try(Generator generator = new Generator()) {
            Gson gson = new Gson();

            CityRMQ cityRMQ = new CityRMQ("GETALLWITHLAZY", new City());
            String jsonCities = generator.call(gson.toJson(cityRMQ), "city");
            Type jsonTypeCities = new TypeToken<ArrayList<City>>(){}.getType();
            generator.setCities(gson.fromJson(jsonCities, jsonTypeCities));

            DirectionWindRMQ directionWindRMQ = new DirectionWindRMQ("GETALL", new DirectionWind());
            String jsonDirectionWind = generator.call(gson.toJson(directionWindRMQ), "directionWind");
            Type jsonTypeDirectionWind = new TypeToken<ArrayList<DirectionWind>>(){}.getType();
            generator.setDirectionWinds(gson.fromJson(jsonDirectionWind, jsonTypeDirectionWind));

            WeatherConditionRMQ weatherConditionRMQ = new WeatherConditionRMQ("GETALLWITHLAZY",new WeatherCondition());
            String jsonWeatherCondition = generator.call(gson.toJson(weatherConditionRMQ), "weatherCondition");
            Type jsonTypeWeatherCondition = new TypeToken<ArrayList<WeatherCondition>>(){}.getType();
            generator.setWeatherConditions(gson.fromJson(jsonWeatherCondition, jsonTypeWeatherCondition));

            OvercastRMQ overcastRMQ = new OvercastRMQ("GETALLWITHLAZY", new Overcast());
            String jsonOvercast = generator.call(gson.toJson(overcastRMQ), "overcast");
            Type jsonTypeOvercast = new TypeToken<ArrayList<Overcast>>(){}.getType();
            generator.setOvercasts(gson.fromJson(jsonOvercast, jsonTypeOvercast));

            generator.generate();

            System.out.println();
            //close(connection);
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
                /*Random random = new Random();
                Random randomTemperature = new Random(-20);*/
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
                String jsonWeatherInformation = this.call(gson.toJson(weatherInformationRMQ), "weatherInformationHandler");
                if(!jsonWeatherInformation.equals("true")) {
                    System.out.println("Don't save: " + gson.toJson(weatherInformationRMQ));
                }
            }
            Thread.sleep(15000);
            localDate = localDate.plusDays(1);
        }
    }

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
