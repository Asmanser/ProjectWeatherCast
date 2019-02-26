import by.andersen.training.models.Accessory;
import by.andersen.training.models.Cap;
import by.andersen.training.models.FootWear;
import by.andersen.training.models.OuterWear;
import by.andersen.training.models.UnderWear;
import by.andersen.training.models.WeatherClothing;
import by.andersen.training.models.WeatherInformation;
import by.andersen.training.models.rabbitmq.WeatherInformationRMQ;
import by.andersen.training.services.rabbitmq.implementation.WeatherInformationParserJsonAndAnswerRMQ;
import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

public class ServerRMQ {

    private ConnectionFactory connectionFactory;

    private List<OuterWear> outerWears;

    private List<Cap> caps;

    private List<UnderWear> underWears;

    private List<FootWear> footWears;

    private List<Accessory> accessories;

    public ServerRMQ(List<OuterWear> outerWears, List<Cap> caps, List<UnderWear> underWears, List<FootWear> footWears, List<Accessory> accessories) {
        this.outerWears = outerWears;
        this.caps = caps;
        this.underWears = underWears;
        this.footWears = footWears;
        this.accessories = accessories;
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
    }

    public void open() {
        Runner runnerWeatherInformation = new Runner(connectionFactory,"weatherInformationHandler");
        Thread threadWeatherInformation = new Thread(runnerWeatherInformation);
        threadWeatherInformation.start();
    }

    private class Runner implements Runnable {

        private ConnectionFactory factory;

        private String RPC_Queue_Name = "";

        public Runner(ConnectionFactory factory, String RPC_Queue_Name) {
            this.factory = factory;
            this.RPC_Queue_Name = RPC_Queue_Name;
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
                        Gson gson = new Gson();
                        WeatherInformationRMQ newWeatherInformationRMQ = gson.fromJson(message,WeatherInformationRMQ.class);
                        WeatherInformation weatherInformation = newWeatherInformationRMQ.getWeatherInformation();
                        weatherInformation.setWeatherClothing(new WeatherClothing());
                        addAccessory(weatherInformation);
                        addFootWear(weatherInformation);
                        addUnderWear(weatherInformation);
                        addOuterWear(weatherInformation);
                        addCap(weatherInformation);
                        Channel newChanel = connection.createChannel();
                        response = this.call(gson.toJson(newWeatherInformationRMQ),"weatherInformation",newChanel);
                    } catch (RuntimeException | InterruptedException e) {
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

        public String call(String message, String requestQueueName,Channel channel) throws IOException, InterruptedException {
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

        private void addAccessory(WeatherInformation weatherInformation) {
            if (weatherInformation.getWeatherCondition().getNameWeatherConditions().equals("Дождь")) {
                for(Accessory accessory : accessories) {
                    if(accessory.getNameAccessory().equals("Зонт")) {
                        weatherInformation.getWeatherClothing().setAccessory(accessory);
                        break;
                    }
                }
            } else {
                if (weatherInformation.getWeatherCondition().getNameWeatherConditions().equals("Шторм") || weatherInformation.getWeatherCondition().getNameWeatherConditions().equals("Дождь с грозой")) {
                    for (Accessory accessory : accessories) {
                        if (accessory.getNameAccessory().equals("Плащ")) {
                            weatherInformation.getWeatherClothing().setAccessory(accessory);
                            break;
                        }
                    }
                } else {
                    if(weatherInformation.getMinAirTemperature() > 20 && (weatherInformation.getOvercast().getNameOvercast().equals("Ясно") ||
                            weatherInformation.getOvercast().getNameOvercast().equals("Нет существенной облачности") ||
                            weatherInformation.getOvercast().getNameOvercast().equals("Нет облаков ниже 3000 м"))) {
                        for (Accessory accessory : accessories) {
                            if (accessory.getNameAccessory().equals("Солнцезащитные очки")) {
                                weatherInformation.getWeatherClothing().setAccessory(accessory);
                                break;
                            }
                        }
                    }
                }
            }
        }

        private void addFootWear(WeatherInformation weatherInformation) {
            if(weatherInformation.getMinAirTemperature() < 3 ) {
                for(FootWear footWear : footWears) {
                    if(footWear.getNameFootWear().equals("Сапоги")) {
                        weatherInformation.getWeatherClothing().setFootWear(footWear);
                        break;
                    }
                }
            } else {
                if(weatherInformation.getMinAirTemperature() >= 3 && weatherInformation.getMinAirTemperature() < 11) {
                    for(FootWear footWear : footWears) {
                        if(footWear.getNameFootWear().equals("Туфли")) {
                            weatherInformation.getWeatherClothing().setFootWear(footWear);
                            break;
                        }
                    }
                } else {
                    if(weatherInformation.getMinAirTemperature() >= 11 && weatherInformation.getMinAirTemperature() < 21) {
                        for(FootWear footWear : footWears) {
                            if(footWear.getNameFootWear().equals("Кроссовки")) {
                                weatherInformation.getWeatherClothing().setFootWear(footWear);
                                break;
                            }
                        }
                    } else {
                        if(weatherInformation.getMinAirTemperature() > 21) {
                            for(FootWear footWear : footWears) {
                                if(footWear.getNameFootWear().equals("Сланцы")) {
                                    weatherInformation.getWeatherClothing().setFootWear(footWear);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        private void addUnderWear(WeatherInformation weatherInformation) {
            if(weatherInformation.getMinAirTemperature() < 3 ) {
                for(UnderWear underWear : underWears) {
                    if(underWear.getNameUnderWear().equals("Теплые штаны")) {
                        weatherInformation.getWeatherClothing().setUnderWear(underWear);
                        break;
                    }
                }
            } else {
                if(weatherInformation.getMinAirTemperature() >= 3 && weatherInformation.getMinAirTemperature() < 21) {
                    for(UnderWear underWear : underWears) {
                        if(underWear.getNameUnderWear().equals("Джинсы")) {
                            weatherInformation.getWeatherClothing().setUnderWear(underWear);
                            break;
                        }
                    }
                } else {
                        if(weatherInformation.getMinAirTemperature() > 21) {
                            for(UnderWear underWear : underWears) {
                                if(underWear.getNameUnderWear().equals("Шорты")) {
                                    weatherInformation.getWeatherClothing().setUnderWear(underWear);
                                    break;
                                }
                            }
                        }
                    }
                }
        }

        private void addOuterWear(WeatherInformation weatherInformation) {
            if(weatherInformation.getMinAirTemperature() < 3 ) {
                for(OuterWear outerWear : outerWears) {
                    if(outerWear.getNameOuterWear().equals("Зимняя куртка")) {
                        weatherInformation.getWeatherClothing().setOuterWear(outerWear);
                        break;
                    }
                }
            } else {
                if(weatherInformation.getMinAirTemperature() >= 3 && weatherInformation.getMinAirTemperature() < 11) {
                    for(OuterWear outerWear : outerWears) {
                        if(outerWear.getNameOuterWear().equals("Ветровка")) {
                            weatherInformation.getWeatherClothing().setOuterWear(outerWear);
                            break;
                        }
                    }
                } else {
                    if(weatherInformation.getMinAirTemperature() >= 11 && weatherInformation.getMinAirTemperature() < 21) {
                        for(OuterWear outerWear : outerWears) {
                            if(outerWear.getNameOuterWear().equals("Байка")) {
                                weatherInformation.getWeatherClothing().setOuterWear(outerWear);
                                break;
                            }
                        }
                    } else {
                        if(weatherInformation.getMinAirTemperature() > 21) {
                            for(OuterWear outerWear : outerWears) {
                                if(outerWear.getNameOuterWear().equals("Майка")) {
                                    weatherInformation.getWeatherClothing().setOuterWear(outerWear);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        private void addCap(WeatherInformation weatherInformation) {
            if(weatherInformation.getMinAirTemperature() < 3 ) {
                for(Cap cap : caps) {
                    if(cap.getNameCap().equals("Теплая шапка")) {
                        weatherInformation.getWeatherClothing().setCap(cap);
                        break;
                    }
                }
            } else {
                if(weatherInformation.getMinAirTemperature() > 20 && (weatherInformation.getOvercast().getNameOvercast().equals("Ясно") ||
                        weatherInformation.getOvercast().getNameOvercast().equals("Нет существенной облачности") ||
                        weatherInformation.getOvercast().getNameOvercast().equals("Нет облаков ниже 3000 м"))) {
                    for(Cap cap : caps) {
                        if(cap.getNameCap().equals("Кепка")) {
                            weatherInformation.getWeatherClothing().setCap(cap);
                            break;
                        }
                    }
                }
            }
        }

    }


}
