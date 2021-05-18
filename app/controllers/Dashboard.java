package controllers;

import java.util.ArrayList;
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
        List<Station> stations = member.stations;

        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).readings.size() != 0) {
                stations.get(i).summary = new Summary(stations.get(i).readings.get(i), stations.get(i));
            }
        }
        render("dashboard.html", stations);
    }

    public static void addStation(String name, double lat, double lng) {
        Member member = Accounts.getLoggedInMember();
        try {
            Station station = new Station(name, lat, lng);
            member.stations.add(station);
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
        //int i = member.stations.indexOf(station);
        member.stations.remove(member.stations.indexOf(station));
        member.save();
        Logger.info("Deleting Station");
        redirect("/dashboard");
    }
}