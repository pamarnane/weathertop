package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Member;
import models.Station;
import models.Reading;
import models.Summary;
import play.Logger;
import play.mvc.Controller;

/**
 * Dashboard controller handles the addition and deletion
 * of stations for the WeatherTop application
 *
 * @author Patrick Marnane
 * @version 1.0
 * @since 2021-05-17
 */
public class Dashboard extends Controller {

    public static void index() {
        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Station> stations = member.getStations();
        Collections.sort(stations, Comparator.comparing(models.Station::getName));

        for (Station station : stations) {
            int i = station.getReadings().size();
            if (i != 0) {
                station.setSummary(new Summary(station.getReadings().get(i - 1), station));
            }
        }
        render("dashboard.html", stations);
    }

    public static void addStation(String name, double lat, double lng) {
        Member member = Accounts.getLoggedInMember();
        try {
            Station station = new Station(name, lat, lng);
            member.getStations().add(station);
            member.save();
            Logger.info("Adding Station" + name);
            redirect("/dashboard");
        } catch (Exception e) {
            System.err.println("Caught exception: " + e);
            redirect("/dashboard.html");
        }

    }

    public static void deleteStation(Long id) {
        Member member = Accounts.getLoggedInMember();

        Station station = Station.findById(id);
        member.getStations().remove(station);
        member.save();
        station.delete();
        Logger.info("Deleting Station");
        redirect("/dashboard");
    }
}