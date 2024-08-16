package com.codecool.szidzse.solarwatch.service;

import com.codecool.szidzse.solarwatch.model.entity.City;
import com.codecool.szidzse.solarwatch.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(Long id, City updatedCity) {
        return cityRepository.findById(id)
                .map(city -> {
                    city.setName(updatedCity.getName());
                    city.setState(updatedCity.getState());
                    city.setCountry(updatedCity.getCountry());
                    city.setLongitude(updatedCity.getLongitude());
                    city.setLatitude(updatedCity.getLatitude());
                    return cityRepository.save(city);
                })
                .orElseThrow(() -> new RuntimeException("City not found with id " + id));
    }

    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }
}
