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

        import com.sun.org.apache.bcel.internal.generic.SWITCH;
        import models.Station;
        import models.Reading;
        import play.Logger;
        import play.mvc.Controller;

        import static controllers.Application.*;

public class Dashboard extends Controller
{
    public static void index()
    {
        Logger.info("Rendering Dashboard");
        List<Station> stations = Station.findAll();
        render ("dashboard.html", stations);
    }

    public static void addStation(String name)
    {
        Station station = new Station(name);
        station.save();
        redirect("/dashboard");
    }



}