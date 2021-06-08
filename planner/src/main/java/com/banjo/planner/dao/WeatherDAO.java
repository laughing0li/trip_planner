package com.banjo.planner.dao;

import com.banjo.planner.pojo.Weather;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherDAO extends JpaRepository<Weather, Integer> {

    Page<Weather> findAllByCity(Pageable pageable, String city);
    List<Weather> findAllByCity(String city);
}
