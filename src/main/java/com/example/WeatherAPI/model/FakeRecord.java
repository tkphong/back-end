package com.example.WeatherAPI.model;


public class FakeRecord
{
    private Integer temperature;
    private Integer humidity;
    private Integer windSpeed;

    public FakeRecord(Integer temperature, Integer humidity, Integer windSpeed) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public FakeRecord() {
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Integer windSpeed) {
        this.windSpeed = windSpeed;
    }
}
