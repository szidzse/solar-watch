package com.codecool.szidzse.solarwatch.exception;

import java.time.LocalDate;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException(LocalDate date) {
        super(String.format("Invalid date or date format: %s . Provide a valid date.", date));
    }
}
