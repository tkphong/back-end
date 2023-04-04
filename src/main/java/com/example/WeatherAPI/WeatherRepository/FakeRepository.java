package com.example.WeatherAPI.WeatherRepository;

import com.example.WeatherAPI.entity.FakeRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FakeRepository extends JpaRepository<FakeRow, Long>
{
    List<FakeRow> findTop30ByOrderByIdDesc();
}
