package com.example.WeatherAPI.WeatherController;


import com.example.WeatherAPI.WeatherService.WeatherService;
import com.example.WeatherAPI.entity.WeatherColumn;
import com.example.WeatherAPI.model.FakeRecord;
import com.example.WeatherAPI.model.WeatherRecord;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api")
public class WeatherController

{
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    @GetMapping("/addLatest")
    public WeatherColumn addLatest()
    {
        return weatherService.addLatest();
    }

    @GetMapping("/getLatest")
    public WeatherRecord getLatestRecord()
    {
        return weatherService.getLatestRecord();
    }

    // @GetMapping("/getRecords")
    // public List<FakeRecord> getListRecords()
    // {
    //     // The passed in string must be in ISO format
    //     return weatherService.getListRecords();
    // }
    @GetMapping("/getRecords")
    public List<WeatherRecord> getListRecords()
    {
        return weatherService.getListRecords();
    }
    
    @GetMapping("/getDiagramRecords")
    public  List<FakeRecord> getDiagramRecords()
    {
        return weatherService.getDiagramRecords();
    }
}

