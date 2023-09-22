package com.threeraredyn.campbooka.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threeraredyn.campbooka.entity.Role;
import com.threeraredyn.campbooka.entity.User;
import com.threeraredyn.campbooka.jpa.UserRepository;
import com.threeraredyn.campbooka.model.UserDashboardResponseDTO;
import com.threeraredyn.campbooka.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if(userOptional.isPresent())
            return userOptional.get();
        else
            return null;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean checkAlreadyExists(String username) {
        return userRepository.existsByEmail(username);
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        
        if(! userOptional.isPresent())
            return null;
        return userOptional.get();
    }

    @Override
    public List<User> findAllByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public UserDashboardResponseDTO getUserDashboardDetails(String username) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<User> userOptional = userRepository.findByEmail(username);

        if(! userOptional.isPresent())
            return null;
        
        UserDashboardResponseDTO userDashboardResponseDTO = modelMapper.map(userOptional.get(), UserDashboardResponseDTO.class);
        userDashboardResponseDTO.setJoinDate(null); // To be changed later!
        return userDashboardResponseDTO; 
    }
    
}