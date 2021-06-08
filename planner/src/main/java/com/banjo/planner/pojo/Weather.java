package com.banjo.planner.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Data
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String country;

    private String city;

    private Double temp;

    private String weather;

    private String time;

}
