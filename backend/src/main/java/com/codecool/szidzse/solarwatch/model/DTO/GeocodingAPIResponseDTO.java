package com.codecool.szidzse.solarwatch.model.DTO;

import java.math.BigDecimal;

public record GeocodingAPIResponseDTO(String name, String state, String country, BigDecimal lat, BigDecimal lon) {
}
