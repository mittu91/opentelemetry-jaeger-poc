package com.demo.project1.dao.entity;

import javax.persistence.*;
import java.io.Serial;
import java.util.Date;

@Entity
@Table(name = "user_sessions")
public class UserSession implements BaseEntity {

    @Serial
    private static final long serialVersionUID = 7469541535884150763L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "session_id", unique = true)
    private String sessionId;

    @Column(name = "session_timeout")
    private Integer timeout;

    @Column(name = "login_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginDate;

    @Column(name = "logout_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logOutDate;

    @Column(name = "active")
    private boolean active;

    @Column(name = "last_access_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAccessDate;

    public UserSession() {
    }

    private UserSession(Builder builder) {
        setId(builder.id);
        setUserId(builder.userId);
        setSessionId(builder.sessionId);
        setTimeout(builder.timeout);
        setLoginDate(builder.loginDate);
        setLogOutDate(builder.logOutDate);
        setActive(builder.active);
        setLastAccessDate(builder.lastAccessDate);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(UserSession copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.userId = copy.getUserId();
        builder.sessionId = copy.getSessionId();
        builder.timeout = copy.getTimeout();
        builder.loginDate = copy.getLoginDate();
        builder.logOutDate = copy.getLogOutDate();
        builder.active = copy.isActive();
        builder.lastAccessDate = copy.getLastAccessDate();
        return builder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Date getLogOutDate() {
        return logOutDate;
    }

    public void setLogOutDate(Date logOutDate) {
        this.logOutDate = logOutDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(Date lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public static final class Builder {
        private Long id;
        private Long userId;
        private String sessionId;
        private Integer timeout;
        private Date loginDate;
        private Date logOutDate;
        private boolean active;
        private Date lastAccessDate;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
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

        public Builder logOutDate(Date logOutDate) {
            this.logOutDate = logOutDate;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public Builder lastAccessDate(Date lastAccessDate) {
            this.lastAccessDate = lastAccessDate;
            return this;
        }

        public UserSession build() {
            return new UserSession(this);
        }
    }
}
