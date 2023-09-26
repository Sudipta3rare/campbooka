package com.threeraredyn.campbooka.controller;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.threeraredyn.campbooka.service.PropertyService;
import com.threeraredyn.campbooka.service.UserService;

@RestController
public class ImageController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);
    
    @GetMapping (
        value = "/api/public/images/property/{filename}", 
        produces = {MediaType.IMAGE_PNG_VALUE}
    )
    public ResponseEntity<?> getPropertyImage(@PathVariable String filename) {
        try {
            ByteArrayResource inputStream = propertyService.getPropertyImage(filename);
            return ResponseEntity.ok().contentLength(inputStream.contentLength()).body(inputStream);
        }
        catch(NoSuchFileException e) {
            LOGGER.error("Cannot find image {}", filename);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (IOException e) {
            LOGGER.error("Error Occured while reading image {}", filename);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(
        value = "/api/public/images/camper/{filename}", 
        produces = {MediaType.IMAGE_PNG_VALUE}
    )
    public ResponseEntity<?> getCamperImage(@PathVariable String filename) {
        try {
            ByteArrayResource inputStream = userService.getCamperImage(filename);
            return ResponseEntity.ok().contentLength(inputStream.contentLength()).body(inputStream);
        }
        catch(NoSuchFileException e) {
            LOGGER.error("Cannot find image {}", filename);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (IOException e) {
            LOGGER.error("Error Occured while reading image {}", filename);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(
        value = "/api/public/images/host/{filename}", 
        produces = {MediaType.IMAGE_PNG_VALUE}
    )
    public ResponseEntity<?> getHostImage(@PathVariable String filename) {
        try {
            ByteArrayResource inputStream = userService.getHostImage(filename);
            return ResponseEntity.ok().contentLength(inputStream.contentLength()).body(inputStream);
        }
        catch(NoSuchFileException e) {
            LOGGER.error("Cannot find image {}", filename);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (IOException e) {
            LOGGER.error("Error Occured while reading image {}", filename);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
