package com.example.skillmatrix.dto;

import com.example.skillmatrix.model.User.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String direction;
    private String currentPosition;
    private String expectedPosition;
    private Role role;
}
