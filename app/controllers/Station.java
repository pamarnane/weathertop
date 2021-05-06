package controllers;

import models.Summary;
import play.Logger;
import play.mvc.Controller;

import java.sql.Timestamp;


public class Station extends Controller {

    public static void index(Long id)
    {
        models.Station station = models.Station.findById(id);
        Logger.info("Rendering station");

        int i = station.readings.size();

        if (i != 0)
        {
            //Summary latestReading = new Summary(station.readings.get(i-1), station);
            station.summary = new Summary(station.readings.get(i-1), station);
            //render ("station.html", station, latestReading);
            render ("station.html", station);
        }
        else
        {
            render("station-new.html", station);
        }

    }

    public static void addReading(Long id, int code, double temp, double windSpeed, int windDirection, int pressure, Timestamp date)
    {
        date = new Timestamp(System.currentTimeMillis());
        //models.Reading newReading = new models.Reading(code, temp, windSpeed, windDirection, pressure);
        models.Reading newReading = new models.Reading(code, temp, windSpeed, windDirection, pressure, date);
        models.Station station = models.Station.findById(id);
        station.readings.add(newReading);
        station.save();
        redirect("/station/" + id);
    }

    public static void deleteReading(Long stationid, Long id)
    {

        models.Reading reading = models.Reading.findById(id);
        models.Station station = models.Station.findById(stationid);
        station.readings.remove(station.readings.indexOf(reading));
        station.save();
        redirect("/station/" + stationid);
    }

}
