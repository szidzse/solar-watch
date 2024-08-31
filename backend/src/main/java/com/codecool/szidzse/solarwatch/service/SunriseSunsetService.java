package com.codecool.szidzse.solarwatch.service;

import com.codecool.szidzse.solarwatch.exception.InvalidDateException;
import com.codecool.szidzse.solarwatch.model.DTO.SolarTimes;
import com.codecool.szidzse.solarwatch.model.DTO.SunriseSunsetAPIResponseDTO;
import com.codecool.szidzse.solarwatch.model.DTO.SunriseSunsetDTO;
import com.codecool.szidzse.solarwatch.model.entity.City;
import com.codecool.szidzse.solarwatch.model.entity.SunriseSunset;
import com.codecool.szidzse.solarwatch.repository.SunriseSunsetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class SunriseSunsetService {
    private final WebClient webClient;

    private final GeocodingService geocodingService;

    private final SunriseSunsetRepository sunriseSunsetRepository;

    @Value("${sunrise-sunset.api.url}")
    private String SUNRISE_SUNSET_API_URL;

    @Autowired
    public SunriseSunsetService(WebClient webClient, GeocodingService geocodingService, SunriseSunsetRepository sunriseSunsetRepository) {
        this.webClient = webClient;
        this.geocodingService = geocodingService;
        this.sunriseSunsetRepository = sunriseSunsetRepository;
    }

    public List<SunriseSunset> findAll() {
        return sunriseSunsetRepository.findAll();
    }

    public List<SunriseSunset> findAllByCityId(Long cityId) {
        return sunriseSunsetRepository.findAllByCityId(cityId);
    }

    public Optional<SunriseSunset> findById(Long id) {
        return sunriseSunsetRepository.findById(id);
    }

    public SunriseSunset save(SunriseSunset sunriseSunset) {
        return sunriseSunsetRepository.save(sunriseSunset);
    }

    public void deleteById(Long id) {
        sunriseSunsetRepository.deleteById(id);
    }

    public SunriseSunsetDTO getSunriseSunset(String cityName, LocalDate date) {
        City city = geocodingService.getCoordinatesFor(cityName);
        BigDecimal latitude = city.getLatitude();
        BigDecimal longitude = city.getLongitude();
        SolarTimes solarTimes = getSolarTimes(latitude, longitude, date);

        SunriseSunset savedSunriseSunset = createAndSaveSunriseSunset(city, date, solarTimes);

        SunriseSunsetDTO sunriseSunsetDTO = new SunriseSunsetDTO();
        sunriseSunsetDTO.setId(savedSunriseSunset.getId());
        sunriseSunsetDTO.setSunrise(LocalDateTime.of(savedSunriseSunset.getDate(), savedSunriseSunset.getSunrise()));
        sunriseSunsetDTO.setSunset(LocalDateTime.of(savedSunriseSunset.getDate(), savedSunriseSunset.getSunset()));
        sunriseSunsetDTO.setDate(savedSunriseSunset.getDate());
        sunriseSunsetDTO.setCityId(city.getId());

        return sunriseSunsetDTO;
    }

    public SolarTimes getSolarTimes(BigDecimal lat, BigDecimal lng, LocalDate date) {
        String url = String.format("%s?lat=%s&lng=%s&date=%s", SUNRISE_SUNSET_API_URL, lat, lng, date);

        SunriseSunsetAPIResponseDTO response = webClient
                .get() // request type
                .uri(url) // request URI
                .retrieve() // sends the actual request
                .bodyToMono(SunriseSunsetAPIResponseDTO.class) // parses the response
                .block(); // waits for the response

        if (response != null && "OK".equals(response.status())) {
            return response.results();
        } else {
            throw new InvalidDateException(date);
        }
    }

    public SunriseSunset createAndSaveSunriseSunset(City city, LocalDate date, SolarTimes solarTimes) {
        SunriseSunset sunriseSunset = new SunriseSunset();
        sunriseSunset.setCity(city);
        sunriseSunset.setDate(date);
        sunriseSunset.setSunrise(convertToLocalTime(solarTimes.sunrise()));
        sunriseSunset.setSunset(convertToLocalTime(solarTimes.sunset()));

        return sunriseSunsetRepository.save(sunriseSunset);
    }

    LocalTime convertToLocalTime(String timeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");
        return LocalTime.parse(timeStr, formatter);
    }
}
