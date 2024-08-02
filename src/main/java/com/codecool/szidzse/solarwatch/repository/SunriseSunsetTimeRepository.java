package com.codecool.szidzse.solarwatch.repository;

import com.codecool.szidzse.solarwatch.model.entity.City;
import com.codecool.szidzse.solarwatch.model.entity.SunriseSunsetTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SunriseSunsetTimeRepository extends JpaRepository<SunriseSunsetTime, Long> {
    Optional<SunriseSunsetTime> findByCityAndDate(City city, LocalDate date);
}
