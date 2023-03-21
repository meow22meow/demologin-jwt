package com.jwt.Service;

import com.jwt.Config.JwtService;
import com.jwt.Repository.UserRepository;
import com.jwt.dto.AuthenticationResponse;
import com.jwt.dto.LoginData;
import com.jwt.dto.RegisterRequest;
import com.jwt.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest request)
    {
        var user= Users.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        return "User registered successfully";
    }
    public AuthenticationResponse login(@RequestBody LoginData data)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.getEmail(),
                        data.getPassword()
                )
        );
        var user=repository.findByEmail(data.getEmail()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}
