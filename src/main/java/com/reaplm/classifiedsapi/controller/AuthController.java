package com.reaplm.classifiedsapi.controller;

import java.time.OffsetDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reaplm.classifiedsapi.dto.AuthResponse;
import com.reaplm.classifiedsapi.dto.LoginRequest;
import com.reaplm.classifiedsapi.dto.RegistrationRequest;
import com.reaplm.classifiedsapi.model.User;
import com.reaplm.classifiedsapi.repository.UserRepository;
import com.reaplm.classifiedsapi.service.JwtService;

import jakarta.persistence.Column;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private AuthenticationManager AuthResponse;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegistrationRequest request) {
    	try {
    	  User user = new User();
    	  user.setEmail(request.getEmail());
    	  user.setUsername(request.getUsername());
    	  user.setPassword(passwordEncoder.encode(request.getPassword()));
    	  userRepository.save(user);
    	  
    	  	return ResponseEntity.ok()
    	            .body(Map.of(
    	                "success", true,
    	                "message", "User registered successfully"
    	            ));
    	    } catch (Exception e) {
    	        return ResponseEntity.badRequest()
    	            .body(Map.of(
    	                "success", false,
    	                "message", "Registration failed: " + e.getMessage()
    	            ));
    	    } 
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
    	try {
	        // Authenticates the user against database credentials
	        authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
	        );
	        
	        // If authentication passes, generate a token
	        String token = jwtService.generateToken(request.getEmail());
	        
	        
	        return ResponseEntity.ok(new AuthResponse(token, "bearer"));
    	}
    	catch(BadCredentialsException ex) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    	}
    }
}

