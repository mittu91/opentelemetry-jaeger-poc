package com.demo.project1.api.model.request;

public class AuthorizeUser {
    private String authToken;

    public AuthorizeUser() {
    }

    public AuthorizeUser(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
