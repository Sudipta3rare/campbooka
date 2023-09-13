package com.threeraredyn.campbooka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.threeraredyn.campbooka.config.JwtTokenUtil;
import com.threeraredyn.campbooka.entity.Role;
import com.threeraredyn.campbooka.entity.User;
import com.threeraredyn.campbooka.model.AuthRequestDTO;
import com.threeraredyn.campbooka.model.AuthResponseDTO;
import com.threeraredyn.campbooka.model.HostDTO;
import com.threeraredyn.campbooka.model.UserDTO;
import com.threeraredyn.campbooka.service.UserService;

@RestController
public class AuthController {
    
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signIn(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequestDTO.getEmail(), authRequestDTO.getPassword()));

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponseDTO authResponseDTO = new AuthResponseDTO();

            authResponseDTO.setEmail(authRequestDTO.getEmail());
            authResponseDTO.setAccessToken(accessToken);

            return ResponseEntity.ok()
                .header("Token", authResponseDTO.getAccessToken())
                .body(authResponseDTO);
        }
        catch(BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO) {

        if(userService.checkAlreadyExists(userDTO.getEmail()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        User user = new User();
        Role role = new Role();

        role.setName("USER");
        role.setId(3L);

        user.setRole(role);
        String[] name = userDTO.getName().split(" ");
        user.setFirstName(name[0]);

        if(name.length == 2)
            user.setLastName(name[1]);
        else if(name.length == 3) {
            user.setMiddleName(name[1]);
            user.setLastName(name[2]);
        }

        user.setEmail(userDTO.getEmail());
        user.setPhoneno(userDTO.getMobileNumber());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/signupHost")
    public ResponseEntity<?> signUpHost(@RequestBody HostDTO hostDTO) {

        if(userService.checkAlreadyExists(hostDTO.getEmail()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        User user = new User();
        Role role = new Role();

        role.setId(2L);
        role.setName("HOST");
        user.setRole(role);

        String[] name = hostDTO.getName().split(" ");
        user.setFirstName(name[0]);

        if(name.length == 2)
            user.setLastName(name[1]);
        else if(name.length == 3) {
            user.setMiddleName(name[1]);
            user.setLastName(name[2]);
        }

        user.setEmail(hostDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(hostDTO.getPassword()));

        userService.save(user);
        return ResponseEntity.ok().build();
    }
}
