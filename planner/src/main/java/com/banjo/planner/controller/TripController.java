package com.banjo.planner.controller;
import com.banjo.planner.pojo.Trip;
import com.banjo.planner.service.TripService;
import com.banjo.planner.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TripController {

    @Autowired
    TripService tripService;

    /**
     * @param ids the two weather's id
     * @return return success message
     */
    @PostMapping("/trips")
    public Results saveTrip(@RequestBody int[] ids) {
        tripService.saveTrip(ids);
        return Results.success();
    }

    /**
     * @return return all trips information
     */
    @GetMapping("/trips")
    public Results getTrips() {
        List<Trip> trips = tripService.getAll();
        return Results.success(trips);
    }
}
