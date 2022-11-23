package com.demo.project1.api.model.response;

import java.util.Date;

public class LoginResponseDTO {
    private Long id;
    private String sessionId;
    private Integer timeout;
    private Date loginDate;
    private boolean stayLoggedIn;
    private String accessToken;
    private String refreshToken;
    private String firstName;
    private String lastName;
    private String dialCode;
    private String contactNo;
    private String emailId;
    private Date createdAt;

    public LoginResponseDTO() {
    }

    private LoginResponseDTO(Builder builder) {
        setId(builder.id);
        setSessionId(builder.sessionId);
        setTimeout(builder.timeout);
        setLoginDate(builder.loginDate);
        setStayLoggedIn(builder.stayLoggedIn);
        setAccessToken(builder.accessToken);
        setRefreshToken(builder.refreshToken);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setDialCode(builder.dialCode);
        setContactNo(builder.contactNo);
        setEmailId(builder.emailId);
        setCreatedAt(builder.createdAt);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(LoginResponseDTO copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.sessionId = copy.getSessionId();
        builder.timeout = copy.getTimeout();
        builder.loginDate = copy.getLoginDate();
        builder.stayLoggedIn = copy.isStayLoggedIn();
        builder.accessToken = copy.getAccessToken();
        builder.refreshToken = copy.getRefreshToken();
        builder.firstName = copy.getFirstName();
        builder.lastName = copy.getLastName();
        builder.dialCode = copy.getDialCode();
        builder.contactNo = copy.getContactNo();
        builder.emailId = copy.getEmailId();
        builder.createdAt = copy.getCreatedAt();
        return builder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public boolean isStayLoggedIn() {
        return stayLoggedIn;
    }

    public void setStayLoggedIn(boolean stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public static final class Builder {
        private Long id;
        private String sessionId;
        private Integer timeout;
        private Date loginDate;
        private boolean stayLoggedIn;
        private String accessToken;
        private String refreshToken;
        private String firstName;
        private String lastName;
        private String dialCode;
        private String contactNo;
        private String emailId;
        private Date createdAt;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder timeout(Integer timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder loginDate(Date loginDate) {
            this.loginDate = loginDate;
            return this;
        }

        public Builder stayLoggedIn(boolean stayLoggedIn) {
            this.stayLoggedIn = stayLoggedIn;
            return this;
        }

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder dialCode(String dialCode) {
            this.dialCode = dialCode;
            return this;
        }

        public Builder contactNo(String contactNo) {
            this.contactNo = contactNo;
            return this;
        }

        public Builder emailId(String emailId) {
            this.emailId = emailId;
            return this;
        }

        public Builder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public LoginResponseDTO build() {
            return new LoginResponseDTO(this);
        }
    }
}
