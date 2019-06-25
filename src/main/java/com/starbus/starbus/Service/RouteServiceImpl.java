package com.starbus.starbus.Service;

import com.starbus.starbus.Domain.Node;
import com.starbus.starbus.Domain.Route;
import com.starbus.starbus.Repository.NodeRepository;
import com.starbus.starbus.Repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService{

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private NodeRepository nodeRepository;

    @Override
    public List<Route> getRoutes(int cityId, String name) {
        if (name.equals("")) {
            return this.routeRepository.findAllByCityId(cityId);
        } else {
            return this.routeRepository.findAllByCityIdAndName(cityId, name);
        }
    }

    @Override
    public HashMap<String, Object> applyRoute(String routeId, String nodeId) throws Exception {
        Optional<Route> fr = this.routeRepository.findById(routeId);
        Optional<Node> fn = this.nodeRepository.findById(nodeId);

        if (!fr.isPresent() || !fn.isPresent())
            throw new Exception("Not Validate Route or Node");
        return null;
    }
}
