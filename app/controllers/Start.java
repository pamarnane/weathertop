package controllers;

import play.mvc.*;

public class Start extends Controller {

    public static void index() {
        render("start.html");
    }

}