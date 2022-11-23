package com.demo.project1.api.model.request;

import java.io.Serializable;

/**
 * {@code UserDetails} is a user detail class that contains user information that is saved in the token.
 *
 * @author Nisheeth Shah
 * @since 1.0.0.RELEASE September 27, 2018
 */
public class UserDetails implements Serializable {

    private static final long serialVersionUID = -2473033073376182227L;
    /**
     * Username
     */
    protected String username;

    protected String fname;

    protected String lname;

    protected long userId;
    protected String sessionId;

    /**
     * User Email
     */
    protected String email;

    /**
     * User password
     */
    protected String password;

    /**
     * Whether user is verified.
     */
    protected boolean verified;

    protected boolean active;

    protected int timeout;

    public static Builder builder() {
        return new Builder();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public static class Builder {

        protected String username;

        protected String fname;

        protected String lname;

        protected String password;

        protected boolean verified;

        protected String email;

        protected boolean active;

        protected long userId;

        protected String sessionId;

        protected int timeout;

        protected Builder() {
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder fname(String fname) {
            this.fname = fname;
            return this;
        }

        public Builder lname(String lname) {
            this.lname = lname;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder verified(boolean verified) {
            this.verified = verified;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public Builder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public UserDetails build() {
            UserDetails userDetails = new UserDetails();
            userDetails.password = password;
            userDetails.username = username;
            userDetails.verified = verified;
            userDetails.active = active;
            userDetails.email = email;
            userDetails.sessionId = sessionId;
            userDetails.userId = userId;
            userDetails.fname = fname;
            userDetails.lname = lname;
            userDetails.timeout = timeout;
            return userDetails;
        }
    }
}
