package com.forecastapp.forecastapp.Model.beans;

/**
 * Created by MelDiSooQi on 3/28/2016.
 */
public class Day {
//Create bean for one weather day
    int id;
    String date;

    int highFahrenheit;
    int highcelsius;

    int lowFahrenheit;
    int lowCelsius;

    String conditions;
    String conditionsIcon_url;

    public Day(int id, String date, int highFahrenheit, int highcelsius, int lowFahrenheit, int lowCelsius, String conditions, String conditionsIcon_url) {
        this.id = id;
        this.date = date;
        this.highFahrenheit = highFahrenheit;
        this.highcelsius = highcelsius;
        this.lowFahrenheit = lowFahrenheit;
        this.lowCelsius = lowCelsius;
        this.conditions = conditions;
        this.conditionsIcon_url = conditionsIcon_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHighFahrenheit() {
        return highFahrenheit;
    }

    public void setHighFahrenheit(int highFahrenheit) {
        this.highFahrenheit = highFahrenheit;
    }

    public int getHighcelsius() {
        return highcelsius;
    }

    public void setHighcelsius(int highcelsius) {
        this.highcelsius = highcelsius;
    }

    public int getLowFahrenheit() {
        return lowFahrenheit;
    }

    public void setLowFahrenheit(int lowFahrenheit) {
        this.lowFahrenheit = lowFahrenheit;
    }

    public int getLowCelsius() {
        return lowCelsius;
    }

    public void setLowCelsius(int lowCelsius) {
        this.lowCelsius = lowCelsius;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getConditionsIcon_url() {
        return conditionsIcon_url;
    }

    public void setConditionsIcon_url(String conditionsIcon_url) {
        this.conditionsIcon_url = conditionsIcon_url;
    }
}
