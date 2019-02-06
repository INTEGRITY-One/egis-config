package com.ionep.egis.services.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class CityTests {
	
	@Test
	public void constructor() throws Exception {
		City city = new City();
		assertNotNull(city);
	}
	
	@Test
	public void allFields() throws Exception {
		City city = new City();
		city.setName("Ashburn");
		city.setPostalCode("20147");
		city.setCountryCode(1);
		city.setLatitude(1.1);
		city.setLongitude(1.1);
		assertEquals("Ashburn", city.getName());
		assertEquals("20147", city.getPostalCode());
		assertEquals(1, city.getCountryCode());
		assertEquals(1.1, city.getLatitude());
		assertEquals(1.1, city.getLongitude());
	}
}