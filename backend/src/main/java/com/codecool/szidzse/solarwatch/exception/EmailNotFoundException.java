package com.codecool.szidzse.solarwatch.exception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String email) {
        super("Email not found: " + email);
    }
}
