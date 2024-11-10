package com.vestas.libraryManagement.controllers;

import com.vestas.libraryManagement.dtos.request.AuthenticationRequest;
import com.vestas.libraryManagement.services.JwtService;
import com.vestas.libraryManagement.services.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailService myUserDetailService;

    @PostMapping("/rest/authenticate")
    public String authenticateAndGetToken(@RequestBody final AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.username(), authenticationRequest.password()
        ));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(myUserDetailService.loadUserByUsername(authenticationRequest.username()));
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }

    @GetMapping("/home")
    public String handleWelcome() {
        return "Welcome to home!";
    }

    @Secured("ROLE_OWNER")
    @GetMapping("/admin/home")
    public String handleAdminHome() {
        return "Welcome to ADMIN home!";
    }

    @Secured("ROLE_CLIENT")
    @GetMapping("/user/home")
    public String handleUserHome() {
        return "Welcome to USER home!";
    }
}
