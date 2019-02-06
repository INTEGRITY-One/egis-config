package com.ionep.egis.services.config.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ionep.egis.services.config.City;

public interface CityRepository extends CrudRepository<City, Long> {
	
	Optional<City> findByName(String name);

}
