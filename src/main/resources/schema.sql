create schema EGIS_WEATHER;

create table EGIS_WEATHER.CITY (
	ID identity unique,
	NAME varchar(80) not null,
	POSTAL_CODE varchar(20),
	COUNTRY_CODE int,
	LATITUDE double,
	LONGITUDE double,
	primary key(ID)
);

create index CITY_NAME_INDEX on EGIS_WEATHER.CITY(NAME);