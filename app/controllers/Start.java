package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Start extends Controller {

    public static void index()
    {
        WeatherConv weatherConvert = new WeatherConv();
        render("start.html");
    }

}