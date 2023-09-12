package com.example.weather;

public class CurrentDay {

    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private double date;
    private String condition;
    private double temp_c;
    private double humidity;
    private double precip_mm;
    private double wind_kph;

    public double getDate() {
        return date;
    }

    public void setDate(double date) {
        this.date = date;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPrecip_mm() {
        return precip_mm;
    }

    public void setPrecip_mm(double precip_mm) {
        this.precip_mm = precip_mm;
    }

    public double getWind_kph() {
        return wind_kph;
    }

    public void setWind_kph(double wind_kph) {
        this.wind_kph = wind_kph;
    }
}
