package com.demo.project1.api.model.request;

import javax.validation.constraints.NotNull;

public class ResetPasswordRequest {

    @NotNull(message = "Please enter previous password")
    private String oldPassword;
    @NotNull(message = "Please enter new password")
    private String newPassword;

    public ResetPasswordRequest() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
