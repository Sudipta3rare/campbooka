package com.threeraredyn.campbooka.service;

import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.User;

@Service
public interface UserService {

    public User findByUsername(String username);
    public void save(User user);
    public boolean checkAlreadyExists(String username);
}