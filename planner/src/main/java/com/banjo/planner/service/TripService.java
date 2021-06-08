package com.banjo.planner.service;

import com.banjo.planner.dao.TripDAO;
import com.banjo.planner.dao.WeatherDAO;
import com.banjo.planner.pojo.Trip;
import com.banjo.planner.pojo.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Provide functions related to maintain Trips
 */

@Service
public class TripService {

    @Autowired
    TripDAO tripDAO;

    @Autowired
    WeatherDAO weatherDAO;


    /**
     * According to the id of the weather selected by the user,
     * packaging it as a trip and stored in the database
     *
     * @param ids Is the id of the weather information
     *            for the two time periods selected by the user.
     */
    public void saveTrip(int[] ids) {
        Trip trip = new Trip();
        List<Weather> list = new ArrayList<>();

        // Get the corresponding weather information according to the weather id
        for (Integer item : ids) {
            Weather weather = weatherDAO.getById(item);
            list.add(weather);
        }
        // Assemble two weather information into a trip
        Weather w1 = list.get(0);
        Weather w2 = list.get(1);

        // set the destination
        trip.setDestination(w1.getCity());

        // The start and end time are used to indicate
        // that the user will arrive at the destination
        // during this time period
        trip.setStart_time(w1.getTime());
        trip.setEnd_time(w2.getTime());
        trip.setMin_temp(w1.getTemp());
        trip.setMax_temp(w2.getTemp());
        trip.setWeather(w2.getWeather());

        tripDAO.save(trip);
    }

    /**
     * @return Used to return all trips information
     */
    public List<Trip> getAll() {
        return tripDAO.findAll();
    }
}
