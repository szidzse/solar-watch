package com.codecool.szidzse.solarwatch.model.payload;

import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
