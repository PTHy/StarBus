package com.starbus.starbus.Controller;

import com.starbus.starbus.Domain.Route;
import com.starbus.starbus.Protocol.ResponseFormat;
import com.starbus.starbus.Protocol.ResponseType;
import com.starbus.starbus.Service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class RouteController {

    private Logger logger = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private RouteService routeService;

    @GetMapping("/api/cities/{cityId}/routes")
    public ResponseEntity getRoutes(@PathVariable int cityId, @RequestParam String name, Errors errors) {
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                logger.warn(error.toString());
            }

            return ResponseEntity
                    .badRequest()
                    .body(new ResponseFormat(ResponseType.VALIDATE_ERROR, null));
        }

        try {
            List<Route> routes = this.routeService.getRoutes(cityId, name);

            return ResponseEntity
                    .ok(new ResponseFormat(ResponseType.GET_ROUTES_SUCCESS, routes));
        } catch (Exception ex) {
            ex.printStackTrace();

            return ResponseEntity
                    .status(500)
                    .body(new ResponseFormat(ResponseType.FAIL, ex.getMessage()));
        }
    }

    @PostMapping("/api/routes/ridebell")
    public ResponseEntity applyRoute(@RequestBody HashMap<String, String> data) {
        try {
            return ResponseEntity
                    .ok(new ResponseFormat(ResponseType.APPLY_ROUTE_SUCCESS, this.routeService.applyRoute(data.get("routeId"), data.get("nodeId"))));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body(new ResponseFormat(ResponseType.FAIL, ex.getMessage()));
        }
    }

}
