package com.ionep.egis.services.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ionep.egis.services.config.repository.CityRepository;

@RestController
public class ConfigController {

    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
	private CityRepository cityRepository;

    @RequestMapping(
    		value="/weather/cities", 
    		method = RequestMethod.GET
    )
    public Collection<City> getAll() {
    	Collection<City> cities = new ArrayList<City>();
    	
		// Retrieve ALL Cities from the PostgreSQL database
		Iterable<City> ci = this.cityRepository.findAll();
		for (Iterator<City> i = ci.iterator(); i.hasNext();) {
			cities.add(i.next());
		}
		
    	return cities;  
	}
    
    @RequestMapping(
			value="/weather/cities/{id}", 
			method = RequestMethod.GET
	)
	public City getSingle(@RequestParam(value="id", defaultValue="0") long id) {
		// Retrieve a single City from the PostreSQL database, by ID
    	Optional<City> city = this.cityRepository.findById(id);
		
    	if (city.isPresent())
    		return city.get();
    	else
    		return null;
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
	    
	    // Store the city to the PostgreSQL database
	 	this.cityRepository.save(newCity);
	    
	    return newCity;
	}
}
