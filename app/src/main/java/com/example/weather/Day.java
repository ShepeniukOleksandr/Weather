package com.example.weather;

public class Day {
    String city = "";
    String condition ="";
    String date = "";

    double avgTemp = 0;
    double rainChance = 0;
    double maxWind = 0;
    double humidity = 0;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public double getRainChance() {
        return rainChance;
    }

    public void setRainChance(double rainChance) {
        this.rainChance = rainChance;
    }

    public double getMaxWind() {
        return maxWind;
    }

    public void setMaxWind(double maxWind) {
        this.maxWind = maxWind;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}