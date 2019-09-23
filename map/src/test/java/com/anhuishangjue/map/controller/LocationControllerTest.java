package com.anhuishangjue.map.controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationControllerTest {

    @Test
    public void getLocation() {
        LocationController locationController = new LocationController();
        try {
            locationController.getLocation();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}