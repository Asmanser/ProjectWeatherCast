package by.andersen.training.weathercast.rmq;

import by.andersen.training.weathercast.models.Accessory;
import by.andersen.training.weathercast.models.Cap;
import by.andersen.training.weathercast.models.FootWear;
import by.andersen.training.weathercast.models.OuterWear;
import by.andersen.training.weathercast.models.UnderWear;
import by.andersen.training.weathercast.models.WeatherClothing;
import by.andersen.training.weathercast.models.WeatherInformation;
import by.andersen.training.weathercast.models.rabbitmq.WeatherInformationRMQ;
import com.google.gson.Gson;
import com.rabbitmq.client.ConnectionFactory;

import java.util.List;

public class WeatherInformationParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    private ConnectionFactory factory;

    private String RPC_Queue_Name = "";

    private List<OuterWear> outerWears;

    private List<Cap> caps;

    private List<UnderWear> underWears;

    private List<FootWear> footWears;

    private List<Accessory> accessories;

    public WeatherInformationParserJsonAndAnswerRMQ(ConnectionFactory factory, String RPC_Queue_Name,
                                                    List<OuterWear> outerWears, List<Cap> caps, List<UnderWear> underWears,
                                                    List<FootWear> footWears, List<Accessory> accessories) {
        this.factory = factory;
        this.RPC_Queue_Name = RPC_Queue_Name;
        this.outerWears = outerWears;
        this.caps = caps;
        this.underWears = underWears;
        this.footWears = footWears;
        this.accessories = accessories;
    }

    @Override
    public String parseAndAnswer(String json) {
        Gson gson = new Gson();
        WeatherInformationRMQ newWeatherInformationRMQ = gson.fromJson(json,WeatherInformationRMQ.class);
        WeatherInformation weatherInformation = newWeatherInformationRMQ.getWeatherInformation();
        weatherInformation.setWeatherClothing(new WeatherClothing());
        addInformation(weatherInformation);
        SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQ = new SendMessageAndAcceptResponseRMQ(factory, RPC_Queue_Name, gson.toJson(newWeatherInformationRMQ));
        sendMessageAndAcceptResponseRMQ.start();
        try {
            sendMessageAndAcceptResponseRMQ.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sendMessageAndAcceptResponseRMQ.getAnswer();
    }

    private void addInformation(WeatherInformation weatherInformation) {
        addAccessory(weatherInformation);
        addFootWear(weatherInformation);
        addUnderWear(weatherInformation);
        addOuterWear(weatherInformation);
        addCap(weatherInformation);
    }

    private void addAccessory(WeatherInformation weatherInformation) {
        if (weatherInformation.getWeatherCondition().getNameWeatherConditions().equals("Rain")) {
            for(Accessory accessory : accessories) {
                if(accessory.getNameAccessory().equals("Umbrella")) {
                    weatherInformation.getWeatherClothing().setAccessory(accessory);
                    break;
                }
            }
        } else {
            if (weatherInformation.getWeatherCondition().getNameWeatherConditions().equals("Storm") || weatherInformation.getWeatherCondition().getNameWeatherConditions().equals("Thunderstorm")) {
                for (Accessory accessory : accessories) {
                    if (accessory.getNameAccessory().equals("Cloak")) {
                        weatherInformation.getWeatherClothing().setAccessory(accessory);
                        break;
                    }
                }
            } else {
                if(weatherInformation.getMinAirTemperature() > 20 && (weatherInformation.getOvercast().getNameOvercast().equals("Clear") ||
                        weatherInformation.getOvercast().getNameOvercast().equals("No significant clouds") ||
                        weatherInformation.getOvercast().getNameOvercast().equals("No clouds below 3000 m"))) {
                    for (Accessory accessory : accessories) {
                        if (accessory.getNameAccessory().equals("Sunglasses")) {
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
                if(footWear.getNameFootWear().equals("Boots")) {
                    weatherInformation.getWeatherClothing().setFootWear(footWear);
                    break;
                }
            }
        } else {
            if(weatherInformation.getMinAirTemperature() >= 3 && weatherInformation.getMinAirTemperature() < 11) {
                for(FootWear footWear : footWears) {
                    if(footWear.getNameFootWear().equals("Shoes")) {
                        weatherInformation.getWeatherClothing().setFootWear(footWear);
                        break;
                    }
                }
            } else {
                if(weatherInformation.getMinAirTemperature() >= 11 && weatherInformation.getMinAirTemperature() < 21) {
                    for(FootWear footWear : footWears) {
                        if(footWear.getNameFootWear().equals("Sneakers")) {
                            weatherInformation.getWeatherClothing().setFootWear(footWear);
                            break;
                        }
                    }
                } else {
                    if(weatherInformation.getMinAirTemperature() > 21) {
                        for(FootWear footWear : footWears) {
                            if(footWear.getNameFootWear().equals("Slates")) {
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
                if(underWear.getNameUnderWear().equals("Warm pants")) {
                    weatherInformation.getWeatherClothing().setUnderWear(underWear);
                    break;
                }
            }
        } else {
            if(weatherInformation.getMinAirTemperature() >= 3 && weatherInformation.getMinAirTemperature() < 21) {
                for(UnderWear underWear : underWears) {
                    if(underWear.getNameUnderWear().equals("Jeans")) {
                        weatherInformation.getWeatherClothing().setUnderWear(underWear);
                        break;
                    }
                }
            } else {
                if(weatherInformation.getMinAirTemperature() > 21) {
                    for(UnderWear underWear : underWears) {
                        if(underWear.getNameUnderWear().equals("Shorts")) {
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
                if(outerWear.getNameOuterWear().equals("Winter Jacket")) {
                    weatherInformation.getWeatherClothing().setOuterWear(outerWear);
                    break;
                }
            }
        } else {
            if(weatherInformation.getMinAirTemperature() >= 3 && weatherInformation.getMinAirTemperature() < 11) {
                for(OuterWear outerWear : outerWears) {
                    if(outerWear.getNameOuterWear().equals("Windbreaker")) {
                        weatherInformation.getWeatherClothing().setOuterWear(outerWear);
                        break;
                    }
                }
            } else {
                if(weatherInformation.getMinAirTemperature() >= 11 && weatherInformation.getMinAirTemperature() < 21) {
                    for(OuterWear outerWear : outerWears) {
                        if(outerWear.getNameOuterWear().equals("Bike")) {
                            weatherInformation.getWeatherClothing().setOuterWear(outerWear);
                            break;
                        }
                    }
                } else {
                    if(weatherInformation.getMinAirTemperature() > 21) {
                        for(OuterWear outerWear : outerWears) {
                            if(outerWear.getNameOuterWear().equals("Mike")) {
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
                if(cap.getNameCap().equals("Warm hat")) {
                    weatherInformation.getWeatherClothing().setCap(cap);
                    break;
                }
            }
        } else {
            if(weatherInformation.getMinAirTemperature() > 20 && (weatherInformation.getOvercast().getNameOvercast().equals("Clear") ||
                    weatherInformation.getOvercast().getNameOvercast().equals("No significant clouds") ||
                    weatherInformation.getOvercast().getNameOvercast().equals("No clouds below 3000 m"))) {
                for(Cap cap : caps) {
                    if(cap.getNameCap().equals("Cap")) {
                        weatherInformation.getWeatherClothing().setCap(cap);
                        break;
                    }
                }
            }
        }
    }
}
