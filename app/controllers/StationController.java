package controllers;

import models.Reading;
import models.Station;
import models.Summary;
import play.Logger;
import play.mvc.Controller;


public class StationController extends Controller {

    public static void index(Long id)
    {
       // WeatherConv weatherConvert = new WeatherConv();
        Station station = Station.findById(id);
        Logger.info("Rendering Readings");


        int i = station.readings.size();

        if (i != 0)
        {
            Summary latestReading = new Summary(station.readings.get(i-1), station);
            render ("station.html", station, latestReading);
        }
        else
        {
            render("station-new.html", station);
        }

    }

    public static void addReading(Long id, int code, double temp, double windSpeed, int windDirection, int pressure)
    {
        models.Reading newReading = new models.Reading(code, temp, windSpeed, windDirection, pressure);
        models.Station station = models.Station.findById(id);
        station.readings.add(newReading);
        station.save();
        redirect("/station/" + id);
    }

}
