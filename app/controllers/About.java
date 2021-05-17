package controllers;

import play.Logger;
import play.mvc.Controller;

/**
 * The WeatherTop application allows users to create and delete stations
 * as well as creating and deleting readings for those stations.
 *
 * @author  Patrick Marnane
 * @version 1.0
 * @since   2021-05-11
 */

public class About extends Controller {

    public static void index()
    {
        Logger.info("Rendering About");
        render ("about.html");
    }
}
