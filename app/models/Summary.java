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

    public String weatherDesc;
    public String weatherIcon;

    public double tempC;
    public double maxTempC;
    public double minTempC;
    public double tempF;


    public int windBft;
    public String windDirectionString;
    public double windChill;
    public double maxWindSpd;
    public double minWindSpd;

    public int pressure;
    public int maxPressure;
    public int minPressure;

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
        this.weatherDesc = Conversions.getWeatherString(latestReading.code);
        this.weatherIcon = Conversions.getWeatherIcon(latestReading.code);
        this.tempC = latestReading.temperature;
        this.tempF = Conversions.calcTempFahrenheit(latestReading.temperature);
        this.windBft = Conversions.calcWindBeaufort(latestReading.windSpeed);
        this.windDirectionString = Conversions.calcWindDirection(latestReading.windDirection);
        this.windChill = Conversions.calcWindChill(latestReading.temperature, latestReading.windSpeed);
        this.pressure = latestReading.pressure;
    }

    public Summary(Reading latestReading, Station station) {
        this.weatherDesc = Conversions.getWeatherString(latestReading.code);
        this.weatherIcon = Conversions.getWeatherIcon(latestReading.code);
        this.tempC = latestReading.temperature;
        this.tempF = Conversions.calcTempFahrenheit(latestReading.temperature);
        this.windBft = Conversions.calcWindBeaufort(latestReading.windSpeed);
        this.windDirectionString = Conversions.calcWindDirection(latestReading.windDirection);
        this.windChill = Conversions.calcWindChill(latestReading.temperature, latestReading.windSpeed);
        this.pressure = latestReading.pressure;

        this.minTempC = Collections.min(Conversions.tempHashSet(station));
        this.maxTempC = Collections.max(Conversions.tempHashSet(station));

        this.minWindSpd = Collections.min(Conversions.windHashSet(station));
        this.maxWindSpd = Collections.max(Conversions.windHashSet(station));

        this.minPressure = Collections.min(Conversions.pressHashSet(station));
        this.maxPressure = Collections.max(Conversions.pressHashSet(station));
    }
}
