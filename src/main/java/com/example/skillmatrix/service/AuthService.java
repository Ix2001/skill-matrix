package com.example.skillmatrix.service;

import com.example.skillmatrix.dto.AuthRequest;
import com.example.skillmatrix.dto.AuthResponse;
import com.example.skillmatrix.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        
        User user = (User) userService.loadUserByUsername(request.getUsername());
        String jwtToken = jwtService.generateToken(user);
        
        return new AuthResponse(jwtToken, user.getUsername(), user.getRole().name());
    }
}
