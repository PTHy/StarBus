package com.starbus.starbus.Repository;

import com.starbus.starbus.Domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, String> {

    @Query(value = "SELECT route.id FROM route WHERE city_id = :cityId AND name LIKE '%:name%'", nativeQuery = true)
    List<Route> findAllByCityIdAndName(@Param("cityId") int cityId, @Param("name") String name);

    List<Route> findAllByCityId(int cityId);
}
