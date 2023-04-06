package com.example.WeatherAPI.WeatherService;

import com.example.WeatherAPI.entity.WeatherColumn;
import com.example.WeatherAPI.model.FakeRecord;
import com.example.WeatherAPI.model.WeatherRecord;

import java.util.List;

public interface WeatherService {

    WeatherRecord getLatestRecord();

    //List<FakeRecord> getListRecords();

    List<FakeRecord> getDiagramRecords();

    WeatherColumn addLatest();
    
    List<WeatherRecord> getListRecords();
}
