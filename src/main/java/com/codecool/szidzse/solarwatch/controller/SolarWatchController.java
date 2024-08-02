package com.codecool.szidzse.solarwatch.controller;

import com.codecool.szidzse.solarwatch.model.DTO.SunriseSunsetDTO;
import com.codecool.szidzse.solarwatch.service.SolarWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/solar-watch")
public class SolarWatchController {
    private SolarWatchService solarWatchService;

    @Autowired
    public SolarWatchController(SolarWatchService solarWatchService) {
        this.solarWatchService = solarWatchService;
    }

    @GetMapping("/sunrise-sunset")
    public SunriseSunsetDTO getSolarTimes(@RequestParam String cityName, @RequestParam LocalDate date) {
        return solarWatchService.getSunriseSunset(cityName, date);
    }
}
