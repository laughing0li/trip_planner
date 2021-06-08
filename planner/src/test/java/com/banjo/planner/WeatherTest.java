package com.banjo.planner;

import com.banjo.planner.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class WeatherTest {

    @Autowired
    WeatherService weatherService;

    @Test
    void contextLoads() {
        //String city = "London";
        //List<Weather> weatherList = weatherService.get(city);
        //for (int i = 0; i < weatherList.size(); i++) {
        //
        //}
        //for (Weather item : weatherList) {
        //    System.out.println(item);
        //    System.out.println(item.getTime());
        //    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        //}
    }
}
