package com.starbus.starbus.Service;

import com.starbus.starbus.Domain.Route;

import java.util.HashMap;
import java.util.List;

public interface RouteService {
    List<Route> getRoutes(int cityId, String name);

    HashMap<String, Object> applyRoute(String routeId, String nodeId) throws Exception;
}
