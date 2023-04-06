package com.example.WeatherAPI.entity;

import javax.persistence.*;

@Entity
@Table(
        name = "fake_records"
)
public class FakeRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            updatable = false
    )
    private Long id;
    private Integer temperature;
    private Integer humidity;
    private Float windSpeed;

    public FakeRow() {
    }

    public FakeRow(Integer temperature, Integer humidity, Float windSpeed) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
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
}
