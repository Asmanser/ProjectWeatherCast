package by.andersen.training.rmq;

import by.andersen.training.models.Accessory;
import by.andersen.training.models.Cap;
import by.andersen.training.models.FootWear;
import by.andersen.training.models.OuterWear;
import by.andersen.training.models.UnderWear;
import by.andersen.training.models.WeatherClothing;
import by.andersen.training.models.WeatherInformation;
import by.andersen.training.models.rabbitmq.WeatherInformationRMQ;
import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
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
