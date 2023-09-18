package com.threeraredyn.campbooka.util;

import com.threeraredyn.campbooka.entity.User;
import com.threeraredyn.campbooka.model.UserProfileUpdateRequestDTO;

public class UserUtil {

    private static boolean notNullAndNotEmpty(String x) {
        return x != null && x.length() != 0; 
    }
    
    private static boolean notNullAndNotEmpty(Long x) {
        return x != null && x != 0; 
    }

    private static boolean notNullAndNotEmpty(Integer x) {
        return x != null && x != 0; 
    }

    public static void updateNonNullAndNonEmptyFields(User user, UserProfileUpdateRequestDTO dto) {
        if(notNullAndNotEmpty(dto.getBio()))
            user.setBio(dto.getBio());

        if(notNullAndNotEmpty(dto.getCamperUrl()))
            user.setCamperUrl(dto.getCamperUrl());

        if(notNullAndNotEmpty(dto.getCity()))
            user.setCity(dto.getCity());

        if(notNullAndNotEmpty(dto.getEmailAddress()))
            user.setEmail(dto.getEmailAddress());

        if(notNullAndNotEmpty(dto.getFacebookUrl()))
            user.setFacebookId(dto.getFacebookUrl());

        if(notNullAndNotEmpty(dto.getFirstName()))
            user.setFirstName(dto.getFirstName());

        if(notNullAndNotEmpty(dto.getInstagramUrl()))
            user.setInstagram(dto.getInstagramUrl());

        if(notNullAndNotEmpty(dto.getLastName()))
            user.setLastName(dto.getLastName());
        
        if(notNullAndNotEmpty(dto.getMiddleName()))
            user.setMiddleName(dto.getMiddleName());

        if(notNullAndNotEmpty(dto.getPersonalUrl()))
            user.setPersonalUrl(dto.getPersonalUrl());

        if(notNullAndNotEmpty(dto.getPhoneNumber()))
            user.setPhoneno(dto.getPhoneNumber());

        if(notNullAndNotEmpty(dto.getPublicLocation()))
            user.setPublicLocation(dto.getPublicLocation());

        if(notNullAndNotEmpty(dto.getState()))
            user.setState(dto.getState());

        if(notNullAndNotEmpty(dto.getStreetAddress()))
            user.setStreet(dto.getStreetAddress());

        if(notNullAndNotEmpty(dto.getSuitNo()))
            user.setSuiteNo(dto.getSuitNo());

        if(notNullAndNotEmpty(dto.getTwitterUrl()))
            user.setTwitter(dto.getTwitterUrl());
        
        if(notNullAndNotEmpty(dto.getZipcode()))
            user.setZipcode(dto.getZipcode());
    }
}
