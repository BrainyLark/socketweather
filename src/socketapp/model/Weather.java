/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketapp.model;

import java.io.Serializable;

/**
 *
 * @author archer
 */
public class Weather implements Serializable {
    private String city;
    private String country;
    private double temp;
    private double humidity;
    private double pressure;
    private double wind_speed;
    private String description;
    
    public Weather(String city, String country, double temp, double humidity, double pressure, 
            double wind_speed, String description) {
        this.city = city;
        this.country = country;
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind_speed = wind_speed;
        this.description = description;
    }
    
    public String getCity() {
        return this.city;
    }
    
    public String getCountry() {
        return this.country;
    }
    
    public double getTemp() {
        return this.temp;
    }
    
    public double getHumidity() {
        return this.humidity;
    }
    
    public double getPressure() {
        return this.pressure;
    }
    
    public double getWindSpeed() {
        return this.wind_speed;
    }
    
    public String getDescription() {
        return this.description;
    }
}
