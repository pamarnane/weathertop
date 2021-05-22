package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.*;

import utils.Conversions;

/**
 * Summary model handles the Station summary values that
 * displayed for the WeatherTop application
 *
 * @author Patrick Marnane
 * @version 1.0
 * @since 2021-05-17
 */
@Entity
public class Summary extends Model {
    private String weatherDesc;
    private String weatherIcon;

    private double tempC;
    private double maxTempC;
    private double minTempC;
    private double tempF;


    private int windBft;
    private String windDirectionString;
    private double windChill;
    private double maxWindSpd;
    private double minWindSpd;

    private int pressure;
    private int maxPressure;
    private int minPressure;

    public Summary(int code, double tempC, double windSpeed, int windDirection, double windVel, int pressure) {
        this.weatherDesc = Conversions.getWeatherString(code);
        this.tempC = tempC;
        this.tempF = Conversions.calcTempFahrenheit(tempC);
        this.windBft = Conversions.calcWindBeaufort(windSpeed);
        this.windDirectionString = Conversions.calcWindDirection(windDirection);
        this.windChill = Conversions.calcWindChill(tempC, windVel);
        this.pressure = pressure;

    }

    public Summary(Reading latestReading) {
        this.weatherDesc = Conversions.getWeatherString(latestReading.getCode());
        this.weatherIcon = Conversions.getWeatherIcon(latestReading.getCode());
        this.tempC = latestReading.getTemperature();
        this.tempF = Conversions.calcTempFahrenheit(latestReading.getTemperature());
        this.windBft = Conversions.calcWindBeaufort(latestReading.getWindSpeed());
        this.windDirectionString = Conversions.calcWindDirection(latestReading.getWindDirection());
        this.windChill = Conversions.calcWindChill(latestReading.getTemperature(), latestReading.getWindSpeed());
        this.pressure = latestReading.getPressure();
    }

    public Summary(Reading latestReading, Station station) {
        this.weatherDesc = Conversions.getWeatherString(latestReading.getCode());
        this.weatherIcon = Conversions.getWeatherIcon(latestReading.getCode());
        this.tempC = latestReading.getTemperature();
        this.tempF = Conversions.calcTempFahrenheit(latestReading.getTemperature());
        this.windBft = Conversions.calcWindBeaufort(latestReading.getWindSpeed());
        this.windDirectionString = Conversions.calcWindDirection(latestReading.getWindDirection());
        this.windChill = Conversions.calcWindChill(latestReading.getTemperature(), latestReading.getWindSpeed());
        this.pressure = latestReading.getPressure();

        this.minTempC = Collections.min(Conversions.tempHashSet(station));
        this.maxTempC = Collections.max(Conversions.tempHashSet(station));

        this.minWindSpd = Collections.min(Conversions.windHashSet(station));
        this.maxWindSpd = Collections.max(Conversions.windHashSet(station));

        this.minPressure = Collections.min(Conversions.pressHashSet(station));
        this.maxPressure = Collections.max(Conversions.pressHashSet(station));
    }
}
