package controllers;

import models.Station;
import play.Logger;
import play.mvc.Controller;

import static controllers.Application.*;

public class Reading extends Controller {
    public static void index(Long id)
    {
        Station station = Station.findById(id);
        Logger.info("Rendering Readings");
        int i = station.readings.size();

        if (i != 0)
        {
            models.Reading latestReading = station.readings.get(station.readings.size() - 1);
            station.weather = readWeatherString(latestReading.code);
            station.tempC = latestReading.temperature;
            station.pressure = latestReading.pressure;
            station.tempF = convertTempFahrenheit(latestReading.temperature);
            station.wind = calcWindBeaufort(latestReading.windSpeed);
            station.windDirection = calcWindDirection(latestReading.windDirection);
            station.windChill = Math.round(calcWindChill(latestReading.temperature, latestReading.windSpeed) * 100.0) / 100.0;
        }
        render ("readings.html", station);
    }

    public static void addReading(Long id, int code, double temp, double windSpeed, int windDirection, int pressure)
    {
        models.Reading newReading = new models.Reading(code, temp, windSpeed, pressure, windDirection);
        Station station = Station.findById(id);
        station.readings.add(newReading);
        station.save();
        redirect("/dashboard/" + id);
    }

}
