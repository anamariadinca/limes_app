package com.example.lime.demolime.entity;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(name = "limes")
public class Lime {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "model")
    private String model;

    @Column(name = "batteryLevel")
    private String batteryLevel;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lon")
    private String lon;


    public Lime(String model, Integer id, String batteryLevel, String lat, String lon) {
        this.id = id;
        this.model = model;
        this.batteryLevel = batteryLevel;
        this.lat = lat;
        this.lon = lon;
    }

    public Lime() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public String getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(String batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public void setCoordinates(String lat, String lon){
        this.setLat(lat);
        this.setLon(lon);
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Lime: " + this.id + ", " + this.model + ", " + this.batteryLevel + ",( " + this.lat + "; " + this.lon + ")";
    }

}
