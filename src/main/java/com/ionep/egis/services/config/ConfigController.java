package com.ionep.egis.services.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(
    			value="/weather/cities", 
    			method = RequestMethod.GET
    )
    public Collection<City> getAll() {
    	Collection<City> cities = new ArrayList<City>();
    	// TODO: Retrieve ALL Cities from the PostgreSQL database
        	
    	cities.add(new City(0, "Ashburn", "20147", 1, 1.1, 1.1));	// Fake it for now...
    	        
    	return cities;
	}
    
    @RequestMapping(
			value="/weather/cities/{id}", 
			method = RequestMethod.GET
	)
	public City getSingle(@RequestParam(value="id", defaultValue="0") long id) {
		City c;
		if (id == 0) {
			// TODO: Retrieve ALL Cities from the PostgreSQL database
	    	
			c = new City(0, "Ashburn", "20147", 1, 1.1, 1.1);	// Fake it for now...
		}
	    else {
	    	// TODO: Retrieve single City from the PostgreSQL database
	    	
	    	c = new City(0, "Ashburn", "20147", 1, 1.1, 1.1);	// Fake it for now...
	    }
	    
		return c;
	}
    
    @RequestMapping(
				value="/weather/cities", 
				method = RequestMethod.POST
	)
	public City create(@RequestParam(value="name") String name,
			@RequestParam(value="postalCode", defaultValue="novalue") String postalCode,
			@RequestParam(value="country", defaultValue="1") int countryCode,
			@RequestParam(value="lat", defaultValue="0.0") double latitude,
			@RequestParam(value="long", defaultValue="0.0") double longitude) {
	    // Create new city
	    City newCity = new City(counter.incrementAndGet(), name, postalCode, countryCode, latitude, longitude);
	    
	    // TODO: Store the city to the PostgreSQL database
	    
	    return newCity;
	}
}
