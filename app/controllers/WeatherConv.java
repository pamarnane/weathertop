/*
package controllers;

import java.util.HashMap;

public class WeatherConv {
    public static HashMap<Integer, String> weatherString;
    public static HashMap<Integer, String> weatherIcon;

    public WeatherConv()
    {
        weatherString = new HashMap<Integer, String>();
        weatherIcon = new HashMap<Integer, String>();
        fillWeatherString();
        fillWeatherIconMap();
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
        weatherIcon.put(100,"sun icon");
        weatherIcon.put(200,"cloud sun icon");
        weatherIcon.put(300,"cloud icon");
        weatherIcon.put(400,"cloud rain icon");
        weatherIcon.put(500,"cloud showers heavy icon");
        weatherIcon.put(600,"umbrella icon");
        weatherIcon.put(700,"snowflake icon");
        weatherIcon.put(800,"bolt icon");
    }

    public static String getWeatherString(int code)
    {
        return weatherString.get(code);
    }

    public static double convertTempFahrenheit(double tempC)
    {
        return (tempC * 9/5  + 32);
    }

    public static int calcWindBeaufort(double wind)
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

    public static String calcWindDirection(int windDirection)
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

    public static double calcWindChill(double temp, double windVel)
    {
        return 13.12 + 0.6215*temp-11.37*Math.pow(windVel, 0.16) + 0.3965*temp*Math.pow(windVel, 0.16);

    }
}
*/
