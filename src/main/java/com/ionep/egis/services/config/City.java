package com.ionep.egis.services.config;

public class City {

    private final long id;
    private final String name;
    private final int countryCode;
    private final String postalCode;
    private final double latitude;
    private final double longitude;

    public City(long id, String name, String postalCode, int countryCode, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
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
    
    public int getCountryCode() {
        return this.countryCode;
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
