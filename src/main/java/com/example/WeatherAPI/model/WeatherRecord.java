package com.example.WeatherAPI.model;

import java.time.Instant;

public class WeatherRecord
{
    private String timeString;
    private Integer temperature;
    private Integer humidity;
    private Float windSpeed;

    public WeatherRecord(String timeString, Integer temperature, Integer humidity, Float windSpeed) {
        this.timeString = timeString;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
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

    public Float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Float windSpeed) {
        this.windSpeed = windSpeed;
    }


    @Override
    public String toString() {
        return "WeatherRecord{" +
                "timeString='" + timeString + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
