package com.codecool.szidzse.solarwatch.model.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CityDTO {
    private Long id;
    private String name;
    private String state;
    private String country;
    private BigDecimal longitude;
    private BigDecimal latitude;
}
