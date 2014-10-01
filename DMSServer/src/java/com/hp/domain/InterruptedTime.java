/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.domain;

/**
 *
 * @author HP
 */
public class InterruptedTime {
    private RoadManagement lastLocation;
    private RoadManagement updatedLocation;
    private float range;
    
    private String lastCity;
    private String updatedCity;

    public String getLastCity() {
        return lastCity;
    }

    public void setLastCity(String lastCity) {
        this.lastCity = lastCity;
    }

    public String getUpdatedCity() {
        return updatedCity;
    }

    public void setUpdatedCity(String updatedCity) {
        this.updatedCity = updatedCity;
    }
    
    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }
    
    public InterruptedTime(){
        
    }
    public InterruptedTime(RoadManagement lastLocation, RoadManagement updatedLocation, float range,
            String lastCity, String updatedCity) {
        this.lastLocation = lastLocation;
        this.updatedLocation = updatedLocation;
        this.range = range;
        this.lastCity = lastCity;
        this.updatedCity = updatedCity;
    }

    public RoadManagement getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(RoadManagement lastLocation) {
        this.lastLocation = lastLocation;
    }

    public RoadManagement getUpdatedLocation() {
        return updatedLocation;
    }

    public void setUpdatedLocation(RoadManagement updatedLocation) {
        this.updatedLocation = updatedLocation;
    }
}
