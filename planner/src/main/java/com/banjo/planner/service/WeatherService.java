package com.banjo.planner.service;

import com.banjo.planner.dao.WeatherDAO;
import com.banjo.planner.pojo.Weather;
import com.banjo.planner.utils.ParseJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Provide functions related to maintain Weather
 */

@Service
public class WeatherService {

    @Autowired
    WeatherDAO weatherDAO;

    /**
     * @param city which city's weather
     * @return a list of Weather Object
     */
    public List<Weather> parseWeather(String city) {
        String url = "http://api.openweathermap.org/data/2.5/forecast?q=" +
                city + "&units=metric&APPID=233edc72584645e44a693426f855e10d";
        ParseJson parseJson = new ParseJson();
        return parseJson.getWeatherInfos(url);
    }

    /**
     * store the weather infos to database
     * @param weatherList a certain city's next five days weather
     */
    public void addWeather(List<Weather> weatherList) {
        for (Weather item : weatherList) {
            weatherDAO.save(item);
        }
    }

    /**
     *
     * @param start the start page
     * @param size  each page will contain how many infos
     * @param city  which city's weather
     * @return Return a page object that encapsulates the weather
     *          information and paging information of the city
     */
    public Page<Weather> get(int start, int size, String city) {
        // used to add weather infos if there no weather infos in the database
        List<Weather> weathers = new ArrayList<>();

        // how to sort the weather infos
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        // set up the pagination
        Pageable pageable = PageRequest.of(start, size, sort);

        // First determine whether the database has weather
        // information for the city, if not, add it first
        List<Weather> list = weatherDAO.findAllByCity(city);
        if (list == null || list.size() == 0) {
            weathers = parseWeather(city);
            addWeather(weathers);
        }
        return weatherDAO.findAllByCity(pageable, city);
    }
}
