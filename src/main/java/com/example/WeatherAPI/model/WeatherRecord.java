package com.example.WeatherAPI.model;

import java.time.Instant;

public class WeatherRecord
{
    private String timeString;
    private Integer temperature;
    private Integer humidity;

    public WeatherRecord(String timeString, Integer temperature, Integer humidity) {
        this.timeString = timeString;
        this.temperature = temperature;
        this.humidity = humidity;
        Instant instant = Instant.parse(timeString);
    }

    public WeatherRecord() {
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
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

    @Override
    public String toString() {
        return "WeatherRecord{" +
                "timeString='" + timeString + '\'' +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                '}';
    }
}
