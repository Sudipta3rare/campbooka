package com.threeraredyn.campbooka.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
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

    @Value("${campbooka.project.path}")
    private String projectPath;

    @Value("${campbooka.project.folder.host}")
    private String hostFolderName;

    @Value("${campbooka.project.folder.camper}")
    private String camperFolderName;

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
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<User, UserDashboardResponseDTO> typeMapper = 
            modelMapper.createTypeMap(User.class, UserDashboardResponseDTO.class);
        
        typeMapper.addMappings(mapping -> {
            mapping.map(src -> src.getFirstName(), UserDashboardResponseDTO::setName);
        });

        Optional<User> userOptional = userRepository.findByEmail(username);

        if(! userOptional.isPresent())
            return null;
        
        UserDashboardResponseDTO userDashboardResponseDTO = modelMapper.map(userOptional.get(), UserDashboardResponseDTO.class);
        userDashboardResponseDTO.setJoinDate(null); // To be changed later!
        return userDashboardResponseDTO; 
    }

    @Override
    public ByteArrayResource getCamperImage(String filename) throws NoSuchFileException, IOException {
        ByteArrayResource inputStream = new
        ByteArrayResource (
            Files.readAllBytes(
                Paths.get(
                    projectPath + 
                    String.format("/assets/%s/", camperFolderName) + 
                    filename + ".png"
                )
            )
        );
        return inputStream;
    }   
    
    @Override
    public ByteArrayResource getHostImage(String filename) throws NoSuchFileException, IOException {
        ByteArrayResource inputStream = new
        ByteArrayResource (
            Files.readAllBytes(
                Paths.get(
                    projectPath 
                    + String.format("/assets/%s/", hostFolderName) 
                    + filename + ".png"
                )
            )
        );
        return inputStream;
    }
}