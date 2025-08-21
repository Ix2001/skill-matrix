package com.example.skillmatrix.controller;

import com.example.skillmatrix.dto.AuthRequest;
import com.example.skillmatrix.dto.AuthResponse;
import com.example.skillmatrix.dto.RegisterRequest;
import com.example.skillmatrix.dto.UserDto;
import com.example.skillmatrix.service.AuthService;
import com.example.skillmatrix.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "API для аутентификации пользователей")
public class AuthController {
    
    private final AuthService authService;
    private final UserService userService;
    
    @PostMapping("/register")
    @Operation(summary = "Регистрация нового пользователя", description = "Регистрирует нового пользователя с ролью USER по умолчанию")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        UserDto userDto = new UserDto();
        userDto.setUsername(registerRequest.getUsername());
        userDto.setEmail(registerRequest.getEmail());
        userDto.setFirstName(registerRequest.getFirstName());
        userDto.setLastName(registerRequest.getLastName());
        userDto.setDirection(registerRequest.getDirection());
        userDto.setCurrentPosition(registerRequest.getCurrentPosition());
        userDto.setExpectedPosition(registerRequest.getExpectedPosition());
        
        UserDto createdUser = userService.createUser(userDto);
        
        // Автоматически аутентифицируем пользователя после регистрации
        AuthRequest authRequest = new AuthRequest(registerRequest.getUsername(), registerRequest.getPassword());
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
    
    @PostMapping("/login")
    @Operation(summary = "Авторизация пользователя", description = "Аутентифицирует пользователя и возвращает JWT токен")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
