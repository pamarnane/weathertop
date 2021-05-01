/*package controllers;

import play.mvc.Controller;

public class Dashboard extends Controller {
    public static void index()
    {
        render("start.html");
    }
}*/
package controllers;

        import java.util.ArrayList;
        import java.util.List;

        import models.Member;
        import models.Station;
        import models.Reading;
        import play.Logger;
        import play.mvc.Controller;

public class Dashboard extends Controller
{
    public static void index()
    {
        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Station> stations = member.stations;
        render ("dashboard.html", stations);
    }

    public static void addStation(String name, double lat, double lng)
    {
        Member member = Accounts.getLoggedInMember();
        Station station = new Station(name, lat, lng);
        member.stations.add(station);
        member.save();
        Logger.info("Adding Station" + name);
        redirect("/dashboard");
    }
    
}