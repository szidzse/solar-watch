package com.codecool.szidzse.solarwatch.model.payload;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
