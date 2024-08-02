package com.codecool.szidzse.solarwatch.repository;

import com.codecool.szidzse.solarwatch.model.City;
import com.codecool.szidzse.solarwatch.model.SunTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SunTimeRepository extends JpaRepository<SunTime, Long> {
    Optional<SunTime> findByCityAndDate(City city, LocalDate date);
}
