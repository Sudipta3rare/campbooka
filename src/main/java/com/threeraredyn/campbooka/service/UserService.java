package com.threeraredyn.campbooka.service;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Role;
import com.threeraredyn.campbooka.entity.User;
import com.threeraredyn.campbooka.model.UserDashboardResponseDTO;

@Service
public interface UserService {

    public User findByUsername(String username);
    public void save(User user);
    public boolean checkAlreadyExists(String username);
    public User findById(Long id);
    public List<User> findAllByRole(Role role);
    public UserDashboardResponseDTO getUserDashboardDetails(String username);
    public ByteArrayResource getCamperImage(String filename) throws NoSuchFileException, IOException;
    public ByteArrayResource getHostImage(String filename) throws NoSuchFileException, IOException;
}