package com.codecool.szidzse.solarwatch.service;

import com.codecool.szidzse.solarwatch.exception.InvalidCityNameException;
import com.codecool.szidzse.solarwatch.model.DTO.GeocodingAPIResponseDTO;
import com.codecool.szidzse.solarwatch.model.entity.City;
import com.codecool.szidzse.solarwatch.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class GeocodingService {
    private RestTemplate restTemplate;

    private CityRepository cityRepository;

    @Value("${geocoding.api.url}")
    private String GEOCODING_API_URL;

    @Value("${geocoding.api.key}")
    private String GEOCODING_API_KEY;

    @Autowired
    public GeocodingService(RestTemplate restTemplate, CityRepository cityRepository) {
        this.restTemplate = restTemplate;
        this.cityRepository = cityRepository;
    }

    public City getCoordinatesFor(String cityName) {
        Optional<City> city = cityRepository.findByNameIgnoreCase(cityName);

        if (city.isEmpty()) {

            GeocodingAPIResponseDTO responseDTO = fetchCoordinatesFor(cityName);

            City fetchedCity = new City(
                    responseDTO.name(),
                    responseDTO.lat(),
                    responseDTO.lon(),
                    responseDTO.country(),
                    responseDTO.state()
            );

            return cityRepository.save(fetchedCity);
        }

        return city.get();
    }


    private GeocodingAPIResponseDTO fetchCoordinatesFor(String cityName) {
        String url = String.format("%s?q=%s&appid=%s", GEOCODING_API_URL, cityName, GEOCODING_API_KEY);

        ResponseEntity<GeocodingAPIResponseDTO[]> response = restTemplate.getForEntity(url, GeocodingAPIResponseDTO[].class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody().length > 0) {
            return response.getBody()[0];
        } else {
            throw new InvalidCityNameException(cityName);
        }
    }
}
