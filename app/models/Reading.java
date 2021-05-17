package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Reading extends Model {
    public int code;
    public double temperature;
    public double windSpeed;
    public int pressure;
    public int windDirection;
    public Date date;

    public Reading(int code, double temperature, double windSpeed, int pressure) {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
    }

    public Reading(int code, double temperature, double windSpeed, int windDirection, int pressure) {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
    }

    public Reading(int code, double temperature, double windSpeed, int windDirection, int pressure, Date date) {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
        this.date = date;
    }

    public int getCode() {
        return code;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getPressure() {
        return pressure;
    }
}
