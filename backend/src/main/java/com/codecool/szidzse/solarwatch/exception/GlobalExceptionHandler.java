package com.codecool.szidzse.solarwatch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
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

    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleAuthenticationException(AuthenticationException exception) {
        return "Unauthorized: " + exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAccessDeniedException(AccessDeniedException exception) {
        return "Forbidden: " + exception.getMessage();
    }

}
