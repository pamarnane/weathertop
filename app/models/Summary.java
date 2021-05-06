package models;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.*;

@Entity
public class Summary extends Model {

    public String weatherDesc;
    public String weatherIcon;

    public double tempC;
    public double maxTempC;
    public double minTempC;
    public double tempF;


    public int windBft;
    public String windDirectionString;
    public double windChill;
    public double maxWindSpd;
    public double minWindSpd;

    public int pressure;
    public int maxPressure;
    public int minPressure;

    public static HashMap<Integer, String> weatherString;
    public static HashMap<Integer, String> weatherIconMap;

    public HashSet<Double> tempHashSet;
    private HashSet<Integer> pressHashSet;
    private HashSet<Double> windHashSet;

    public Summary()
    {
        weatherString = new HashMap<Integer, String>();
        weatherIconMap = new HashMap<Integer, String>();
        fillWeatherString();
        fillWeatherIconMap();
    }

    public Summary(int code, double tempC, double windSpeed, int windDirection, double windVel, int pressure)
    {
        weatherString = new HashMap<Integer, String>();
        weatherIconMap = new HashMap<Integer, String>();
        fillWeatherString();
        fillWeatherIconMap();

        this.weatherDesc = getWeatherString(code);
        this.tempC = tempC;
        this.tempF = convertTempFahrenheit(tempC);
        this.windBft = calcWindBeaufort(windSpeed);
        this.windDirectionString = calcWindDirection(windDirection);
        this.windChill = calcWindChill(tempC, windVel);
        this.pressure = pressure;

    }

    public Summary(Reading latestReading)
    {
        weatherString = new HashMap<Integer, String>();
        weatherIconMap = new HashMap<Integer, String>();

        fillWeatherString();
        fillWeatherIconMap();

        this.weatherDesc = getWeatherString(latestReading.code);
        this.weatherIcon = getWeatherIcon(latestReading.code);
        this.tempC = latestReading.temperature;
        this.tempF = convertTempFahrenheit(latestReading.temperature);
        this.windBft = calcWindBeaufort(latestReading.windSpeed);
        this.windDirectionString = calcWindDirection(latestReading.windDirection);
        this.windChill = calcWindChill(latestReading.temperature, latestReading.windSpeed);
        this.pressure = latestReading.pressure;
    }

    public Summary(Reading latestReading, Station station)
    {
        weatherString = new HashMap<Integer, String>();
        weatherIconMap = new HashMap<Integer, String>();

        fillWeatherString();
        fillWeatherIconMap();

    //    station.tempHashSet();
    //    station.windHashSet();
    //    station.pressHashSet();


        this.weatherDesc = getWeatherString(latestReading.code);
        this.weatherIcon = getWeatherIcon(latestReading.code);
        this.tempC = latestReading.temperature;
        this.tempF = convertTempFahrenheit(latestReading.temperature);
        this.windBft = calcWindBeaufort(latestReading.windSpeed);
        this.windDirectionString = calcWindDirection(latestReading.windDirection);
        this.windChill = calcWindChill(latestReading.temperature, latestReading.windSpeed);
        this.pressure = latestReading.pressure;

       // this.minTempC = Collections.min(station.tempHashSet);
        this.minTempC = Collections.min(tempHashSet(station));
       // this.maxTempC = Collections.max(station.tempHashSet);
        this.maxTempC = Collections.max(tempHashSet(station));

        this.minWindSpd = Collections.min(windHashSet(station));
        this.maxWindSpd = Collections.max(windHashSet(station));

        this.minPressure = Collections.min(pressHashSet(station));
        this.maxPressure = Collections.max(pressHashSet(station));

    }

    private void fillWeatherString()
    {
        weatherString.put(100,"Clear");
        weatherString.put(200,"Partial Clouds");
        weatherString.put(300,"Cloudy");
        weatherString.put(400,"Light Showers");
        weatherString.put(500,"Heavy Showers");
        weatherString.put(600,"Rain");
        weatherString.put(700,"Snow");
        weatherString.put(800,"Thunder");
    }

    private void fillWeatherIconMap()
    {
        weatherIconMap.put(100,"sun icon");
        weatherIconMap.put(200,"cloud sun icon");
        weatherIconMap.put(300,"cloud icon");
        weatherIconMap.put(400,"cloud rain icon");
        weatherIconMap.put(500,"cloud showers heavy icon");
        weatherIconMap.put(600,"umbrella icon");
        weatherIconMap.put(700,"snowflake icon");
        weatherIconMap.put(800,"bolt icon");
    }

   private String getWeatherString(int code)
    {
        return weatherString.get(code);
    }

    private String getWeatherIcon(int code)
    {
        return weatherIconMap.get(code);
    }

    private double convertTempFahrenheit(double tempC)
    {
        return (tempC * 9/5  + 32);
    }

    private int calcWindBeaufort(double wind)
    {
        int windBeaufort = 0;


        if (wind == 0)
        {
            windBeaufort = 0;
        }
        else if (wind > 1 && wind <= 5)
        {
            windBeaufort = 1;
        }
        else if (wind > 5 && wind <= 11)
        {
            windBeaufort = 2;
        }
        else if (wind >= 12 && wind <= 19)
        {
            windBeaufort = 3;
        }
        else if (wind >= 20 && wind <= 28)
        {
            windBeaufort = 4;
        }
        else if (wind >= 29 && wind <= 38)
        {
            windBeaufort = 5;
        }
        else if (wind >= 39 && wind <= 49)
        {
            windBeaufort = 6;
        }
        else if (wind >= 50 && wind <= 61)
        {
            windBeaufort = 7;
        }
        else if (wind >= 62 && wind <= 74)
        {
            windBeaufort = 8;
        }
        else if (wind >= 75 && wind <= 88)
        {
            windBeaufort = 9;
        }
        else if (wind >= 89 && wind <= 102)
        {
            windBeaufort = 10;
        }
        else if (wind >= 103 && wind <= 117)
        {
            windBeaufort = 11;
        }
        return windBeaufort;
    }

    private String calcWindDirection(int windDirection)
    {
        String windString = "";

        if (windDirection > 348.75 && windDirection <= 11.25)
        {
            windString = "N";
        }
        else if (windDirection > 11.25 && windDirection <= 33.75)
        {
            windString = "NNE";
        }
        else if (windDirection > 33.75 && windDirection <= 56.25)
        {
            windString = "NE";
        }
        else if (windDirection > 56.25 && windDirection <= 78.75)
        {
            windString = "ENE";
        }
        else if (windDirection > 78.75 && windDirection <= 101.25)
        {
            windString = "E";
        }
        else if (windDirection > 101.25 && windDirection <= 123.75)
        {
            windString = "ESE";
        }
        else if (windDirection > 123.75 && windDirection <= 146.25)
        {
            windString = "SE";
        }
        else if (windDirection > 146.25 && windDirection <= 168.75)
        {
            windString = "SSE";
        }
        else if (windDirection > 168.75 && windDirection <= 191.25)
        {
            windString = "S";
        }
        else if (windDirection > 191.25 && windDirection <= 213.75)
        {
            windString = "SSW";
        }
        else if (windDirection > 213.75 && windDirection <= 236.25)
        {
            windString = "SW";
        }
        else if (windDirection > 236.25 && windDirection <= 258.75)
        {
            windString = "WSW";
        }
        else if (windDirection > 258.75 && windDirection <= 281.25)
        {
            windString = "W";
        }
        else if (windDirection > 281.25 && windDirection <= 303.75)
        {
            windString = "WNW";
        }
        else if (windDirection > 303.75 && windDirection <= 326.25)
        {
            windString = "W";
        }
        else if (windDirection > 326.25 && windDirection <= 348.75)
        {
            windString = "WNW";
        }

        return windString;
    }

    private double calcWindChill(double temp, double windVel)
    {
        //Math.round(WeatherConv.calcWindChill(latestReading.temperature, latestReading.windSpeed) * 100.0) / 100.0;
        return Math.round(13.12 + 0.6215*temp-11.37*Math.pow(windVel, 0.16) + 0.3965*temp*Math.pow(windVel, 0.16) * 100.0) / 100.0;

    }

    public HashSet<Double> tempHashSet(Station station)
    {
        tempHashSet = new HashSet<>();
        for (int i = 0; i < station.readings.size(); i++)
        {
            tempHashSet.add(station.readings.get(i).temperature);
        }
        return tempHashSet;
    }

    public HashSet<Integer> pressHashSet(Station station)
    {
        pressHashSet = new HashSet<>();
        for (int i = 0; i < station.readings.size(); i++) {
            pressHashSet.add(station.readings.get(i).pressure);
        }
        return pressHashSet;
    }

    public HashSet<Double> windHashSet(Station station)
    {
        windHashSet = new HashSet<>();
        for (int i = 0; i < station.readings.size(); i++) {
            windHashSet.add(station.readings.get(i).windSpeed);
        }
        return windHashSet;
    }
}
