package com.example.skillmatrix.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    
    private String username;
    
    private String password;
    
    private String email;
    
    private String firstName;
    
    private String lastName;
    
    private String direction;
    
    private String currentPosition;
    
    private String expectedPosition;
}
