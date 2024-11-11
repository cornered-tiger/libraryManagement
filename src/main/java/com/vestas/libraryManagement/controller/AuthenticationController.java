package com.vestas.libraryManagement.controller;

import com.vestas.libraryManagement.dto.request.AuthenticationRequest;
import com.vestas.libraryManagement.service.CustomUserDetailService;
import com.vestas.libraryManagement.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailService customUserDetailService;

    public AuthenticationController(final AuthenticationManager authenticationManager, final JwtService jwtService, final CustomUserDetailService customUserDetailService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailService = customUserDetailService;
    }

    @PostMapping("/rest/authenticate")
    public String authenticateAndGetToken(@RequestBody final AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.username(), authenticationRequest.password()
        ));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(customUserDetailService.loadUserByUsername(authenticationRequest.username()));
        } else {
            log.error("username or password are incorrect");
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }
}
