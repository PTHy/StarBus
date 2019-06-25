package com.starbus.starbus.Repository;

import com.starbus.starbus.Domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
