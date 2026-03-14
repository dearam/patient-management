package com.example.auth_svc.service;

import com.example.auth_svc.dto.LoginRequestDTO;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.auth_svc.util.JwtUtil;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public Optional<String> authenticate(LoginRequestDTO request){
        System.out.println("Email: " + request.getEmail());
        return userService.findByEmail(request.getEmail())
                .map(user -> {
                    System.out.println("User found");

                    boolean match = passwordEncoder.matches(
                            request.getPassword(),
                            user.getPassword()
                    );

                    System.out.println("Password match: " + match);

                    if(!match) return null;

                    return jwtUtil.generateToken(user.getEmail(), user.getRole());
                });
    }

    public boolean validateToken(String token){
        try {
            jwtUtil.validateToken(token);
            return true;
        }catch (JwtException e){
            return false;
        }
    }
}
