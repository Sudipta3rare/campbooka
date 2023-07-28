package com.threeraredyn.campbooka.model;

public class AuthResponseDTO {
    
    private String email;
    private String accessToken;
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    
}
