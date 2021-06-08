package com.banjo.planner.controller;

import com.banjo.planner.pojo.Weather;
import com.banjo.planner.service.WeatherService;
import com.banjo.planner.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;



@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    /**
     * @param start start page, default is 0
     * @param size how many weather a page, default is 8
     * @param city which city's weather
     * @return weather infos and the pagination infos
     */
    @GetMapping("/weather/{city}")
    public Results getWeather(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(defaultValue = "8") int size,
            @PathVariable(value = "city") String city) {
        Page<Weather> list = weatherService.get(start, size, city);
        return Results.success(list);
    }


}
