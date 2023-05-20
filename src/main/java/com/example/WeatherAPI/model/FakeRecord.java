package com.example.WeatherAPI.model;


public class FakeRecord
{
    private Integer temperature;
    private Integer humidity;

    public FakeRecord(Integer temperature, Integer humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
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

}
