package com.threeraredyn.campbooka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.threeraredyn.campbooka.config.JwtTokenUtil;
import com.threeraredyn.campbooka.entity.User;
import com.threeraredyn.campbooka.model.AuthRequestDTO;
import com.threeraredyn.campbooka.model.AuthResponseDTO;

@RestController
public class AuthController {
    
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signIn(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponseDTO authResponseDTO = new AuthResponseDTO();

            authResponseDTO.setEmail(authRequestDTO.getEmail());
            authResponseDTO.setAccessToken(accessToken);

            return ResponseEntity.ok().body(authResponseDTO);
        }
        catch(BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
