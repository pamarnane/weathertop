package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;


public class StationController extends Controller {

    public static void index(Long id)
    {
        WeatherConv weatherConvert = new WeatherConv();
        Station station = Station.findById(id);
        Logger.info("Rendering Readings");
        Reading latestReading = station.readings.get(station.readings.size() - 1);

        int i = station.readings.size();

        if (i != 0)
        {
            latestReading.weatherDesc = WeatherConv.getWeatherString(latestReading.code);
            latestReading.tempF = WeatherConv.convertTempFahrenheit(latestReading.temperature);
            latestReading.windSpeed = WeatherConv.calcWindBeaufort(latestReading.windSpeed);
            latestReading.windDirectionString = WeatherConv.calcWindDirection(latestReading.windDirection);
            latestReading.windChill = Math.round(WeatherConv.calcWindChill(latestReading.temperature, latestReading.windSpeed) * 100.0) / 100.0;

        }
        render ("station.html", station, latestReading);
    }

    public static void addReading(Long id, int code, double temp, double windSpeed, int windDirection, int pressure)
    {
        models.Reading newReading = new models.Reading(code, temp, windSpeed, pressure, windDirection);
        models.Station station = models.Station.findById(id);
        station.readings.add(newReading);
        station.save();
        redirect("/station/" + id);
    }

}
