package utils;

import models.Station;

import java.util.HashMap;
import java.util.HashSet;

public class Conversions {
    private static HashMap<Integer, String> weatherString;
    private static HashMap<Integer, String> weatherIconMap;

    private static void fillWeatherString() {
        weatherString = new HashMap<Integer, String>();
        weatherString.put(100, "Clear");
        weatherString.put(200, "Partial Clouds");
        weatherString.put(300, "Cloudy");
        weatherString.put(400, "Light Showers");
        weatherString.put(500, "Heavy Showers");
        weatherString.put(600, "Rain");
        weatherString.put(700, "Snow");
        weatherString.put(800, "Thunder");
    }

    private static void fillWeatherIconMap() {
        weatherIconMap = new HashMap<Integer, String>();
        weatherIconMap.put(100, "sun icon");
        weatherIconMap.put(200, "cloud sun icon");
        weatherIconMap.put(300, "cloud icon");
        weatherIconMap.put(400, "cloud rain icon");
        weatherIconMap.put(500, "cloud showers heavy icon");
        weatherIconMap.put(600, "umbrella icon");
        weatherIconMap.put(700, "snowflake icon");
        weatherIconMap.put(800, "bolt icon");
    }

    public static String getWeatherString(int code) {
        fillWeatherString();
        return weatherString.get(code);
    }

    public static String getWeatherIcon(int code) {
        fillWeatherIconMap();
        return weatherIconMap.get(code);
    }

    public static double calcTempFahrenheit(double tempC) {
        return (double) Math.round((tempC * 9 / 5 + 32) * 100) / 100;
    }

    public static int calcWindBeaufort(double wind) {
        int windBeaufort = 0;

        if (wind == 0) {
            windBeaufort = 0;
        } else if (wind > 1 && wind <= 5) {
            windBeaufort = 1;
        } else if (wind > 5 && wind <= 11) {
            windBeaufort = 2;
        } else if (wind >= 12 && wind <= 19) {
            windBeaufort = 3;
        } else if (wind >= 20 && wind <= 28) {
            windBeaufort = 4;
        } else if (wind >= 29 && wind <= 38) {
            windBeaufort = 5;
        } else if (wind >= 39 && wind <= 49) {
            windBeaufort = 6;
        } else if (wind >= 50 && wind <= 61) {
            windBeaufort = 7;
        } else if (wind >= 62 && wind <= 74) {
            windBeaufort = 8;
        } else if (wind >= 75 && wind <= 88) {
            windBeaufort = 9;
        } else if (wind >= 89 && wind <= 102) {
            windBeaufort = 10;
        } else if (wind >= 103 && wind <= 117) {
            windBeaufort = 11;
        }
        return windBeaufort;
    }

    public static String calcWindDirection(int windDirection) {
        String windString = "";

        if (windDirection > 348.75 && windDirection <= 11.25) {
            windString = "North";
        } else if (windDirection > 11.25 && windDirection <= 33.75) {
            windString = "North North East";
        } else if (windDirection > 33.75 && windDirection <= 56.25) {
            windString = "North East";
        } else if (windDirection > 56.25 && windDirection <= 78.75) {
            windString = "East North East";
        } else if (windDirection > 78.75 && windDirection <= 101.25) {
            windString = "East";
        } else if (windDirection > 101.25 && windDirection <= 123.75) {
            windString = "East South East";
        } else if (windDirection > 123.75 && windDirection <= 146.25) {
            windString = "South East";
        } else if (windDirection > 146.25 && windDirection <= 168.75) {
            windString = "South South East";
        } else if (windDirection > 168.75 && windDirection <= 191.25) {
            windString = "South";
        } else if (windDirection > 191.25 && windDirection <= 213.75) {
            windString = "South South West";
        } else if (windDirection > 213.75 && windDirection <= 236.25) {
            windString = "South West";
        } else if (windDirection > 236.25 && windDirection <= 258.75) {
            windString = "West South West";
        } else if (windDirection > 258.75 && windDirection <= 281.25) {
            windString = "West";
        } else if (windDirection > 281.25 && windDirection <= 303.75) {
            windString = "West North West";
        } else if (windDirection > 303.75 && windDirection <= 326.25) {
            windString = "North West";
        } else if (windDirection > 326.25 && windDirection <= 348.75) {
            windString = "North North West";
        }

        return windString;
    }

    public static double calcWindChill(double temp, double windVel) {
        //Math.round(WeatherConv.calcWindChill(latestReading.temperature, latestReading.windSpeed) * 100.0) / 100.0;
        return Math.round(13.12 + 0.6215 * temp - 11.37 * Math.pow(windVel, 0.16) + 0.3965 * temp * Math.pow(windVel, 0.16) * 100.0) / 100.0;

    }

    /**
     * Read in a station and create
     * a HashSet of the temperature readings
     * and return them.
     *
     * @param station Station information
     * @return tempHashSet a HashSet of the stations
     * temperature readings
     */
    public static HashSet<Double> tempHashSet(Station station) {
        HashSet<Double> tempHashSet = new HashSet<>();
        for (int i = 0; i < station.getReadings().size(); i++) {
            tempHashSet.add(station.getReadings().get(i).getTemperature());
        }
        return tempHashSet;
    }

    /**
     * Read in a station and create
     * a HashSet of the pressure readings
     * and return them.
     *
     * @param station Station information
     * @return pressHashSet a HashSet of the stations
     * pressure readings
     */
    public static HashSet<Integer> pressHashSet(Station station) {
        HashSet<Integer> pressHashSet = new HashSet<>();
        for (int i = 0; i < station.getReadings().size(); i++) {
            pressHashSet.add(station.getReadings().get(i).getPressure());
        }
        return pressHashSet;
    }

    /**
     * Read in a station and create
     * a HashSet of the wind readings
     * and return them.
     *
     * @param station Station information
     * @return windHashSet a HashSet of the stations
     * wind readings
     */
    public static HashSet<Double> windHashSet(Station station) {
        HashSet<Double> windHashSet = new HashSet<>();
        for (int i = 0; i < station.getReadings().size(); i++) {
            windHashSet.add(station.getReadings().get(i).getWindSpeed());
        }
        return windHashSet;
    }
}
