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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "date_time"
    )
    private LocalDateTime dateTime;
    private Integer humidity;
    private Integer temperature;

    public WeatherColumn() {
    }

    public WeatherColumn(Long id,
                         LocalDateTime dateTime,
                         Integer humidity,
                         Integer temperature
                        ) {
        this.id = id;
        this.dateTime = dateTime;
        this.humidity = humidity;
        this.temperature = temperature;

    }

    public WeatherColumn(LocalDateTime dateTime,
                         Integer humidity,
                         Integer temperature
                        )
    {
        this.dateTime = dateTime;
        this.humidity = humidity;
        this.temperature = temperature;

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


    @Override
    public String toString() {
        return "WeatherColumn{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                '}';
    }
}
