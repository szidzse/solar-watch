package com.codecool.szidzse.solarwatch.service;

import com.codecool.szidzse.solarwatch.model.entity.City;
import com.codecool.szidzse.solarwatch.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CityServiceTest {
    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        City city1 = new City(1L, "Budapest", "BP", "Hungary", new BigDecimal("47.4979"), new BigDecimal("19.0402"));
        City city2 = new City(2L, "Paris", "Île-de-France", "France", new BigDecimal("48.8566"), new BigDecimal("2.3522"));
        when(cityRepository.findAll()).thenReturn(Arrays.asList(city1, city2));

        List<City> cities = cityService.findAll();

        assertEquals(2, cities.size());
        assertEquals("Budapest", cities.get(0).getName());
        assertEquals("Paris", cities.get(1).getName());
        verify(cityRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Found() {
        City city = new City(1L, "Budapest", "BP", "Hungary", new BigDecimal("47.4979"), new BigDecimal("19.0402"));
        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));

        Optional<City> foundCity = cityService.findById(1L);

        assertTrue(foundCity.isPresent());
        assertEquals("Budapest", foundCity.get().getName());
        verify(cityRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(cityRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<City> foundCity = cityService.findById(1L);

        assertFalse(foundCity.isPresent());
        verify(cityRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        City city = new City(null, "Budapest", "BP", "Hungary", new BigDecimal("47.4979"), new BigDecimal("19.0402"));
        City savedCity = new City(1L, "Budapest", "BP", "Hungary", new BigDecimal("47.4979"), new BigDecimal("19.0402"));
        when(cityRepository.save(any(City.class))).thenReturn(savedCity);

        City result = cityService.save(city);

        assertNotNull(result.getId());
        assertEquals("Budapest", result.getName());
        verify(cityRepository, times(1)).save(city);
    }

    @Test
    void testUpdateCity_Success() {
        City existingCity = new City(1L, "Budapest", "BP", "Hungary", new BigDecimal("47.4979"), new BigDecimal("19.0402"));
        City updatedCity = new City(null, "Budapest Updated", "BP", "Hungary", new BigDecimal("47.4979"), new BigDecimal("19.0402"));

        when(cityRepository.findById(1L)).thenReturn(Optional.of(existingCity));
        when(cityRepository.save(any(City.class))).thenReturn(updatedCity);

        City result = cityService.updateCity(1L, updatedCity);

        assertEquals("Budapest Updated", result.getName());
        verify(cityRepository, times(1)).findById(1L);
        verify(cityRepository, times(1)).save(existingCity);
    }

    @Test
    void testUpdateCity_NotFound() {
        City updatedCity = new City(null, "Budapest Updated", "BP", "Hungary", new BigDecimal("47.4979"), new BigDecimal("19.0402"));
        when(cityRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> cityService.updateCity(1L, updatedCity));
        assertEquals("City not found with id 1", exception.getMessage());
        verify(cityRepository, times(1)).findById(1L);
        verify(cityRepository, times(0)).save(any(City.class));
    }

    @Test
    void testDeleteById() {
        cityService.deleteById(1L);

        verify(cityRepository, times(1)).deleteById(1L);
    }
}
