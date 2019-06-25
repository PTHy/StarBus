package com.starbus.starbus.Controller;

import com.starbus.starbus.Protocol.ResponseFormat;
import com.starbus.starbus.Protocol.ResponseType;
import com.starbus.starbus.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/api/test")
    public boolean syncCities() {
        return this.cityService.syncCities();
    }

    @GetMapping("/api/cities")
    public ResponseEntity getCities() {
        try {
            return ResponseEntity
                    .ok(new ResponseFormat(ResponseType.GET_CITIES_SUCCESS, this.cityService.getCities()));
        } catch (Exception error) {
            error.printStackTrace();

            return ResponseEntity
                    .status(500)
                    .body(new ResponseFormat(ResponseType.FAIL, error.getMessage()));
        }
    }
}
