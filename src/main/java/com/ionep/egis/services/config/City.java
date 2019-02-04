package com.ionep.egis.services.config;

public class City {

    private final long id;
    private final String name;
    private final String postalCode;
    private final double latitude;
    private final double longitude;

    public City(long id, String name, String postalCode, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
    
    public String getPostalCode() {
        return this.postalCode;
    }
    
    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
}
