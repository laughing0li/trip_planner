package com.banjo.planner.pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Data
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String destination;

    private Double max_temp;

    private Double min_temp;

    private String start_time;

    private String end_time;

    private String weather;

}
