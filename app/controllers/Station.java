package controllers;

import models.Summary;
import models.Trending;

import play.Logger;
import play.mvc.Controller;

import java.sql.Timestamp;
import java.util.Date;

import static models.Trending.*;

/**
 * Station controller handles the management of station
 * readings for the WeatherTop application
 *
 * @author Patrick Marnane
 * @version 1.0
 * @since 2021-05-17
 */
public class Station extends Controller {

    public void index(Long id) {
        models.Station station = models.Station.findById(id);
        Trending trendingVals = trendingVals(station.readings);
        Logger.info("Rendering station");

        if (station.readings.size() != 0) {
            station.summary = new Summary(station.readings.get(station.readings.size() - 1), station);
            render("station.html", station, trendingVals);
        } else {
            render("station.html", station);
        }

    }

    public static void addReading(Long id, int code, double temp, double windSpeed, int windDirection, int pressure) {
        Date date = new Timestamp(System.currentTimeMillis());
        try {
            models.Reading newReading = new models.Reading(code, temp, windSpeed, windDirection, pressure, date);
            models.Station station = models.Station.findById(id);
            station.readings.add(newReading);
            station.save();
            redirect("/station/" + id);
        } catch (Exception e) {
            System.err.println("Caught exception: " + e);
            redirect("/station/" + id);
        }
    }

    public static void deleteReading(Long stationid, Long id) {
        models.Reading reading = models.Reading.findById(id);
        models.Station station = models.Station.findById(stationid);
        station.readings.remove(station.readings.indexOf(reading));
        station.save();
        redirect("/station/" + stationid);
    }

}
