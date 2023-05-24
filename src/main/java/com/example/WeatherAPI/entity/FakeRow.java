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
    private Integer humidity;
    private Integer temperature;

    public FakeRow() {
    }

    public FakeRow(Integer humidity, Integer temperature) {
        this.humidity = humidity;
        this.temperature = temperature;
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
