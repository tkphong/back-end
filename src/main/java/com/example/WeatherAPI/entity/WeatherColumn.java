package com.example.WeatherAPI.entity;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(
        name = "weather_records",
        indexes = @Index(name = "time_index", columnList = "date_time")
)
public class WeatherColumn
{
    @Id
    @SequenceGenerator(
            name = "weather_sequence",
            sequenceName = "weather_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "weather_sequence"
    )
    @Column(
            updatable = false
    )
    private Long id;
    @Column(
            name = "date_time"
    )
    private LocalDateTime dateTime;
    private Integer temperature;
    private Integer humidity;
    private Float windSpeed;

    public WeatherColumn() {
    }

    public WeatherColumn(Long id,
                         LocalDateTime dateTime,
                         Integer temperature,
                         Integer humidity,
                         Float windSpeed) {
        this.id = id;
        this.dateTime = dateTime;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public WeatherColumn(LocalDateTime dateTime,
                         Integer temperature,
                         Integer humidity,
                         Float windSpeed)
    {
        this.dateTime = dateTime;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
        return "WeatherColumn{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                '}';
    }
}