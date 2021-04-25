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

public class Dashboard extends Controller
{
    public static void index()
    {
        Logger.info("Rendering Dashboard");

        List<Station> stations = Station.findAll();
        //List<Reading> readings = Reading.findAll();
        for (Station station : stations)
        {
           // station.latestLoc = station.readings.size() - 1;
            //int i = station.readings.size() - 1;
            Reading latestReading =station.readings.get(station.readings.size() - 1);
            station.weather = getWeatherString(latestReading);
            station.tempC = latestReading.temperature;
            station.tempF = getTempFahrenheit(latestReading.temperature);
            station.wind = getWindString(latestReading.windSpeed);
        }

        render ("dashboard.html", stations);
    }

    public static String getWeatherString(Reading latestReading)
    {
        String weatherString = "";

        switch(latestReading.code){
            case 100:
                weatherString = "Clear";
            break;
            case 200:
                weatherString = "Partial Clouds";
            break;
            case 300:
                weatherString = "Cloudy";
            break;
            case 400:
                weatherString = "Light Showers";
            break;
            case 500:
                weatherString = "Heavy Showers";
            break;
            case 600:
                weatherString = "Rain";
            break;
            case 700:
                weatherString = "Snow";
            break;
            case 800:
                weatherString = "Thunder";
            break;
        }
        return weatherString;
    }

    public static double getTempFahrenheit(double tempC)
    {
        return (tempC * 9/5  + 32);
    }

    public static int getWindString(double wind)
    {
        int windBeaufort = 0;

        if (wind == 0)
        {
            windBeaufort = 0;
        }
        else if (wind > 1 && wind <= 5)
        {
            windBeaufort = 1;
        }
        else if (wind > 5 && wind <= 11)
        {
            windBeaufort = 2;
        }
        else if (wind >= 12 && wind <= 19)
        {
            windBeaufort = 3;
        }
        else if (wind >= 20 && wind <= 28)
        {
            windBeaufort = 4;
        }
        else if (wind >= 29 && wind <= 38)
        {
            windBeaufort = 5;
        }
        else if (wind >= 39 && wind <= 49)
        {
            windBeaufort = 6;
        }
        else if (wind >= 50 && wind <= 61)
        {
            windBeaufort = 7;
        }
        else if (wind >= 62 && wind <= 74)
        {
            windBeaufort = 8;
        }
        else if (wind >= 75 && wind <= 88)
        {
            windBeaufort = 9;
        }
        else if (wind >= 89 && wind <= 102)
        {
            windBeaufort = 10;
        }
        else if (wind >= 103 && wind <= 117)
        {
            windBeaufort = 11;
        }
        return windBeaufort;
    }


}