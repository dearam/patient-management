package com.example.auth_svc.controller;

import com.example.auth_svc.dto.LoginRequestDTO;
import com.example.auth_svc.dto.LoginResponseDTO;
import com.example.auth_svc.model.User;
import com.example.auth_svc.service.AuthService;
import com.example.auth_svc.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    public AuthController(AuthService authService, UserService userService) {
        this.userService = userService;
        this.authService = authService;
    }

    @Operation(summary = "User login endpoint")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
        // Placeholder for actual authentication logic

        Optional<String> tokenOptional = authService.authenticate(requestDTO);

        System.out.println("tokenOptional: " + tokenOptional.isEmpty());
        if(tokenOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = tokenOptional.get();
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(summary = "validate token endpoint")
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authHeader) {
        // Placeholder for actual token validation logic
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Authorization header");
        }
        return authService.validateToken(authHeader.substring(7))
                ? ResponseEntity.ok("Token is valid")
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    @GetMapping("/health")
    public String healthCheck(){
        return "Auth Service is up and running!";
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
