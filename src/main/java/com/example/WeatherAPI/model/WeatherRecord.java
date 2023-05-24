package com.example.WeatherAPI.model;

import java.time.Instant;

public class WeatherRecord
{
    private String timeString;
    private Integer humidity;
    private Integer temperature;


    public WeatherRecord(String timeString, Integer humidity, Integer temperature) {
        this.timeString = timeString;
        this.humidity = humidity;
        this.temperature = temperature;
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
                ", humidity=" + temperature +
                '}';
    }
}
