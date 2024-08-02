package com.codecool.szidzse.solarwatch.exception;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String date) {
        super(String.format("Invalid date or date format: %s . Provide a valid date.", date));
    }
}
