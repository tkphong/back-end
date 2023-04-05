package com.example.WeatherAPI.WeatherService;

import com.example.WeatherAPI.WeatherRepository.WeatherRepository;
import com.example.WeatherAPI.entity.WeatherColumn;
import com.example.WeatherAPI.model.WeatherRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class WeatherServiceTest {
    @Autowired
    private WeatherService weatherService;
    @MockBean
    private WeatherRepository weatherRepository;
    @BeforeEach
    void setUp() {
        WeatherColumn weatherColumn = new WeatherColumn();
        weatherColumn.setHumidity(64);
        Mockito.when(weatherRepository.findTopByOrderByIdDesc()).thenReturn(weatherColumn);
    }
    @Test
    public void alwaysGetTheLatestRecord()
    {
        WeatherRecord weatherRecord = weatherService.getLatestRecord();
        assertEquals(weatherRecord.getHumidity(), 64);
    }
}