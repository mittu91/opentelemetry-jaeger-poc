package com.demo.project1.api.model.response;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class UserSessionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3789140424360014666L;
    private Long id;
    private Long userId;
    private String sessionId;
    private Date loginDate;
    private boolean active = true;
    private boolean stayLoggedIn;

    public UserSessionDTO() {
    }

    private UserSessionDTO(Builder builder) {
        setId(builder.id);
        setSessionId(builder.sessionId);
        setLoginDate(builder.loginDate);
        setActive(builder.active);
        setStayLoggedIn(builder.stayLoggedIn);
        setUserId(builder.userId);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(UserSessionDTO copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.sessionId = copy.getSessionId();
        builder.loginDate = copy.getLoginDate();
        builder.active = copy.isActive();
        builder.stayLoggedIn = copy.isStayLoggedIn();
        builder.userId = copy.getUserId();
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

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isStayLoggedIn() {
        return stayLoggedIn;
    }

    public void setStayLoggedIn(boolean stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public static final class Builder {
        private Long id;
        private String sessionId;
        private Date loginDate;
        private boolean active;
        private boolean stayLoggedIn;
        private Long userId;

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
        
        public Builder loginDate(Date loginDate) {
            this.loginDate = loginDate;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public Builder stayLoggedIn(boolean stayLoggedIn) {
            this.stayLoggedIn = stayLoggedIn;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserSessionDTO build() {
            return new UserSessionDTO(this);
        }
    }
}
