package com.codecool.szidzse.solarwatch.controller;

import com.codecool.szidzse.solarwatch.model.Location;
import com.codecool.szidzse.solarwatch.model.SolarTimes;
import com.codecool.szidzse.solarwatch.service.GeocodingService;
import com.codecool.szidzse.solarwatch.service.SolarTimesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/solar-watch")
public class SolarWatchController {
    private final SolarTimesService solarTimesService;
    private final GeocodingService geocodingService;

    public SolarWatchController(SolarTimesService solarTimesService, GeocodingService geocodingService) {
        this.solarTimesService = solarTimesService;
        this.geocodingService = geocodingService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/sunrise-sunset")
    public ResponseEntity<SolarTimes> getSolarTimes(@RequestParam String cityName, @RequestParam String date) {
        Location location = geocodingService.getCoordinates(cityName);
        SolarTimes solarTimes = solarTimesService.getSolarTimes(location.lat(), location.lng(), date);
        return ResponseEntity.ok(solarTimes);
    }
}
