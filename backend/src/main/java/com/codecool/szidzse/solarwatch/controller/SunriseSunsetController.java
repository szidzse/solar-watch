package com.codecool.szidzse.solarwatch.controller;

import com.codecool.szidzse.solarwatch.model.DTO.SunriseSunsetDTO;
import com.codecool.szidzse.solarwatch.model.entity.SunriseSunset;
import com.codecool.szidzse.solarwatch.service.SunriseSunsetService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sunrise-sunset")
@AllArgsConstructor
public class SunriseSunsetController {
    private final SunriseSunsetService sunriseSunsetService;

    @GetMapping("/any")
    public SunriseSunsetDTO getSolarTimes(
            @RequestParam String cityName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return sunriseSunsetService.getSunriseSunset(cityName, date);
    }

    @GetMapping(path = "")
    public List<SunriseSunset> getAllSunriseSunsets() {
        return sunriseSunsetService.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SunriseSunset> getSunriseSunsetById(@PathVariable Long id) {
        return sunriseSunsetService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "")
    public SunriseSunset createSunriseSunset(@RequestBody SunriseSunset sunriseSunset) {
        return sunriseSunsetService.save(sunriseSunset);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteSunriseSunset(@PathVariable Long id) {
        sunriseSunsetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
