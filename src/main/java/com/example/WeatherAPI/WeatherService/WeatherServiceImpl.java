package com.example.WeatherAPI.WeatherService;

import com.example.WeatherAPI.WeatherRepository.FakeRepository;
import com.example.WeatherAPI.WeatherRepository.WeatherRepository;
import com.example.WeatherAPI.entity.FakeRow;
import com.example.WeatherAPI.entity.WeatherColumn;
import com.example.WeatherAPI.model.FakeRecord;
import com.example.WeatherAPI.model.WeatherRecord;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class WeatherServiceImpl  implements WeatherService
{
    private final WeatherRepository weatherRepository;

    private final FakeRepository fakeRepository;
    private List<FakeRecord> diagramRecords;
    private List<WeatherRecord> listRecords;

    public WeatherServiceImpl(WeatherRepository weatherRepository, FakeRepository fakeRepository) {
        this.weatherRepository = weatherRepository;
        this.fakeRepository = fakeRepository;
        this.diagramRecords = new ArrayList<>();
        this.listRecords = new ArrayList<>();
    }
    @Override
    public WeatherRecord getLatestRecord() {
        WeatherColumn weatherColumn  = weatherRepository.findTopByOrderByIdDesc();
        WeatherRecord weatherRecord = new WeatherRecord();
        BeanUtils.copyProperties(weatherColumn, weatherRecord);
        weatherRecord.setTimeString(String.valueOf(weatherColumn.getDateTime()));
        return weatherRecord;
    }

    // @Override
    // public List<FakeRecord> getListRecords()
    // {
    //     List<FakeRow> fakeRowList = fakeRepository.findAll();
    //     List<FakeRecord> fakeRecordList = fakeRowList
    //     //List<FakeRecord> listRecords = fakeRowList
    //             .stream().map(
    //                     row -> new FakeRecord( row.getTemperature(),
    //                             row.getHumidity()
    //                             )
    //             ).collect(Collectors.toList());
    //     return fakeRecordList;
    // }
    @Override
    public List<WeatherRecord> getListRecords() {
        List<WeatherColumn> weatherColumnList = weatherRepository.findAll();
        List<WeatherRecord> weatherRecordList = weatherColumnList
                .stream().map(
                        column -> {
                            WeatherRecord weatherRecord = new WeatherRecord();
                            BeanUtils.copyProperties(column, weatherRecord);
                            weatherRecord.setTimeString(String.valueOf(column.getDateTime()));
                            return weatherRecord;
                        }
                ).collect(Collectors.toList());
        this.listRecords = weatherRecordList;
        return weatherRecordList;
    }

    @Override
    public List<FakeRecord> getDiagramRecords()
    {
        List<FakeRow> fakeRowList = fakeRepository.findTop30ByOrderByIdDesc();
        //List<FakeRecord> fakeRecordList = fakeRowList
        List<FakeRecord> diagramRecords = fakeRowList
                .stream().map(
                        row -> new FakeRecord(
                                row.getTemperature(),
                                row.getHumidity()
                                //row.getWindSpeed()
                        )
                ).collect(Collectors.toList());
        //return fakeRecordList;
        return diagramRecords;
    }

    // @Override
    // public List<FakeRecord> getDiagramRecords() {
    //     return diagramRecords;
    // }
    
    @Override
    public WeatherColumn addLatest() {
        WeatherColumn weatherColumn = new WeatherColumn();

        RestTemplate restTemplate =  new RestTemplate();
        String  temperature = "https://io.adafruit.com/api/v2/fong1668vn/feeds/data-sensor.weather-temperature";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(temperature, String.class);
        if(responseEntity.getStatusCode() == HttpStatus.OK)
        {
            JSONObject jsonObject = new JSONObject(responseEntity.getBody());
            weatherColumn.setTemperature(jsonObject.getInt("last_value"));
            Instant instant = Instant.parse(jsonObject.getString("updated_at"));
            weatherColumn.setDateTime(LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Ho_Chi_Minh")));
        }
        else
        {
            throw  new IllegalStateException("We could not get the result");
        }
        String humidity = "https://io.adafruit.com/api/v2/fong1668vn/feeds/data-sensor.weather-humidity";
        responseEntity = restTemplate.getForEntity(humidity, String.class);
        if(responseEntity.getStatusCode() == HttpStatus.OK)
        {
            JSONObject jsonObject = new JSONObject(responseEntity.getBody());
            weatherColumn.setHumidity(jsonObject.getInt("last_value"));
            Instant instant = Instant.parse(jsonObject.getString("updated_at"));
            
        }
        else
        {
            throw  new IllegalStateException("We could not get the result");
        }
        // String windSpeed = "https://io.adafruit.com/api/v2/fong1668vn/feeds/data-sensor.weather-windspeed";
        // responseEntity = restTemplate.getForEntity(windSpeed, String.class);
        // if(responseEntity.getStatusCode() == HttpStatus.OK)
        // {
        //     JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        //     weatherColumn.setWindSpeed(jsonObject.getFloat("last_value"));
        //     Instant instant = Instant.parse(jsonObject.getString("updated_at"));        }
        // else
        // {
        //     throw  new IllegalStateException("We could not get the result");
        // }
    //     //weatherRepository.save(weatherColumn);
    //     //return weatherColumn;
    //     FakeRecord fakeRecord = new FakeRecord(
    //         weatherColumn.getTemperature(),
    //         weatherColumn.getHumidity(),
    //         weatherColumn.getWindSpeed()
    // );

    // // add the new FakeRecord to the diagramRecords list
    // diagramRecords.add(fakeRecord);

    // // save the weather data to the database
    // weatherRepository.save(weatherColumn);

    // return weatherColumn;

            // add code to retrieve data from Adafruit API

            FakeRecord fakeRecord = new FakeRecord(
                weatherColumn.getTemperature(),
                weatherColumn.getHumidity()
                //weatherColumn.getWindSpeed()
            );
    
            // add the new FakeRecord to the diagramRecords list
            diagramRecords.add(fakeRecord);

            // save the FakeRecord data to the fakeRow table
            FakeRow fakeRow = new FakeRow(
                fakeRecord.getTemperature(),
                fakeRecord.getHumidity()
                //fakeRecord.getWindSpeed()
            );
            fakeRepository.save(fakeRow);
    
            // save the weather data to the database
            weatherRepository.save(weatherColumn);
        // add new record to the list of records
        WeatherRecord weatherRecord = new WeatherRecord();
        BeanUtils.copyProperties(weatherColumn, weatherRecord);
        weatherRecord.setTimeString(String.valueOf(weatherColumn.getDateTime()));
        this.listRecords.add(weatherRecord);
        return weatherColumn;
    }
    
}