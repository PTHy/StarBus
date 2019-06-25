package com.starbus.starbus.Service;

import com.starbus.starbus.Domain.City;

import java.util.List;

public interface CityService {
    public List<City> getCities();
    public boolean syncCities();
}
