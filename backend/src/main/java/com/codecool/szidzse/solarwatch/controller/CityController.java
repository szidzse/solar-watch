package com.codecool.szidzse.solarwatch.controller;

import com.codecool.szidzse.solarwatch.model.DTO.CityDTO;
import com.codecool.szidzse.solarwatch.model.entity.City;
import com.codecool.szidzse.solarwatch.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/cities")
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping(path = "")
    public ResponseEntity<List<CityDTO>> getAllCities() {
        List<City> cities = cityService.findAll();
        List<CityDTO> cityDTOs =  cities.stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(cityDTOs);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id) {
        return cityService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "")
    public City createCity(@RequestBody City city) {
        return cityService.save(city);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City city) {
        return ResponseEntity.ok(cityService.updateCity(id, city));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private CityDTO convertToDTO(City city) {
        CityDTO dto = new CityDTO();
        dto.setId(city.getId());
        dto.setName(city.getName());
        dto.setState(city.getState());
        dto.setCountry(city.getCountry());
        dto.setLongitude(city.getLongitude());
        dto.setLatitude(city.getLatitude());
        return dto;
    }
}
