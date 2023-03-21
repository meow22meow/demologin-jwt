package com.jwt.Controller;

import com.jwt.Repository.UserRepository;
import com.jwt.Service.AuthService;
import com.jwt.dto.AuthenticationResponse;
import com.jwt.dto.LoginData;
import com.jwt.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthenticationController {
    private final AuthService service;
    private final UserRepository repository;
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request)
    {
        return service.register(request);
    }
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginData request)
    {
        return service.login(request);
    }
}
