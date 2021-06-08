package com.banjo.planner.dao;

import com.banjo.planner.pojo.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripDAO extends JpaRepository<Trip, Integer> {
}
