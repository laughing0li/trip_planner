package com.banjo.planner;

import com.banjo.planner.pojo.Weather;
import com.banjo.planner.service.TripService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TripTest {

    @Autowired
    TripService tripService;

    @Test
    @Transactional
    void contextLoads() {
        int[] ids = new int[2];
        ids[0] = 1;
        ids[1] = 2;
        //Weather weather = tripService.getTrip(ids);
        //System.out.println(weather);
    }
}
