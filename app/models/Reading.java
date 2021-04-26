package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Reading extends Model {
    public int code;
    public double temperature;
    public double windSpeed;
    public int pressure;
    public int windDirection;

    public Reading(int code, double temperature, double windSpeed, int pressure) {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
    }

    public Reading(int code, double temperature, double windSpeed, int pressure, int windDirection) {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
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
