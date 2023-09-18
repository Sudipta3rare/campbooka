package com.threeraredyn.campbooka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.threeraredyn.campbooka.entity.Role;
import com.threeraredyn.campbooka.entity.User;
import com.threeraredyn.campbooka.model.UserDashboardResponseDTO;
import com.threeraredyn.campbooka.model.UserProfileRequestDTO;
import com.threeraredyn.campbooka.model.UserProfileUpdateRequestDTO;
import com.threeraredyn.campbooka.service.UserService;
import com.threeraredyn.campbooka.util.UserUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
class UserController {

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/api/user")
    @ResponseBody
    public User findUser(@RequestParam String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.save(user);
    }

    @GetMapping("/api/host/{id}")
    public User getHostById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/api/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/api/admin/getHosts")
    @ResponseBody
    public List<User> getHostList() {
        Role role = new Role();
        role.setId(2L);
        role.setName("HOST");
        return userService.findAllByRole(role);
    }   
    
    @GetMapping("/api/admin/getUsers")
    @ResponseBody
    public List<User> getUserList() {
        Role role = new Role();
        role.setId(3L);
        role.setName("USER");
        return userService.findAllByRole(role);
    }

    @PostMapping("/api/user/getDashboardProfile")
    public ResponseEntity<?> getUserDashboardProfile(@RequestBody UserProfileRequestDTO userProfileRequestDTO) {
        UserDashboardResponseDTO userDashboardResponseDTO = userService.getUserDashboardDetails(userProfileRequestDTO.getEmail());
        if(userDashboardResponseDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(userDashboardResponseDTO);
    }

    @PostMapping("/api/user/updateProfileData")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileUpdateRequestDTO userProfileUpdateRequestDTO) {
        
        if(!userService.checkAlreadyExists(userProfileUpdateRequestDTO.getUsername()))
            ResponseEntity.badRequest().build();

        User camper = userService.findByUsername(userProfileUpdateRequestDTO.getUsername());
        UserUtil.updateNonNullAndNonEmptyFields(camper, userProfileUpdateRequestDTO);
        userService.save(camper);
        return ResponseEntity.ok().build();
    }
}