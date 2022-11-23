package com.demo.project1.api.model.request;

import javax.validation.constraints.NotNull;

public class LoginUserRequest {

    @NotNull(message = "Please enter email address")
    private String email;
    @NotNull(message = "Please enter password")
    private String password;

    public LoginUserRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
