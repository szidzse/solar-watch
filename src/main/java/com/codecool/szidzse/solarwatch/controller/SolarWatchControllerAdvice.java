package com.codecool.szidzse.solarwatch.controller;

import com.codecool.szidzse.solarwatch.exception.InvalidCityNameException;
import com.codecool.szidzse.solarwatch.exception.InvalidDateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SolarWatchControllerAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidCityNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidCityNameExceptionHandler(InvalidCityNameException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InvalidDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidDateExceptionHandler(InvalidDateException exception) {
        return exception.getMessage();
    }
}
