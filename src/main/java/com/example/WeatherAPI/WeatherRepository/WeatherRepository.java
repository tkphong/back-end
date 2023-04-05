package com.example.WeatherAPI.WeatherRepository;


import com.example.WeatherAPI.entity.WeatherColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherColumn, Long>
{
    WeatherColumn findTopByOrderByIdDesc();
}
