package com.codecool.szidzse.solarwatch.exception;

public class InvalidCityNameException extends RuntimeException {
    public InvalidCityNameException(String cityName) {
        super(String.format("Invalid city name: %s . Provide a valid city name!", cityName));
    }
}
