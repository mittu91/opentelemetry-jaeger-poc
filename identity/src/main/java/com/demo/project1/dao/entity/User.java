package com.demo.project1.dao.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serial;
import java.util.Date;

@Entity
@Table(name = "user_master")
public class User implements BaseEntity {

    @Serial
    private static final long serialVersionUID = -7579280862955237980L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    private Boolean isActive;
    private Date deletedAt;
    private String firstName;
    private String lastName;
    private String dialCode;
    private String contactNo;
    private String password;
    private String emailId;

    public User() {
    }

    private User(Builder builder) {
        id = builder.id;
        createdAt = builder.createdAt;
        updatedAt = builder.updatedAt;
        isActive = builder.isActive;
        deletedAt = builder.deletedAt;
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setDialCode(builder.dialCode);
        setContactNo(builder.contactNo);
        setPassword(builder.password);
        setEmailId(builder.emailId);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(User copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.createdAt = copy.getCreatedAt();
        builder.updatedAt = copy.getUpdatedAt();
        builder.isActive = copy.getActive();
        builder.deletedAt = copy.getDeletedAt();
        builder.firstName = copy.getFirstName();
        builder.lastName = copy.getLastName();
        builder.dialCode = copy.getDialCode();
        builder.contactNo = copy.getContactNo();
        builder.password = copy.getPassword();
        builder.emailId = copy.getEmailId();
        return builder;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public static final class Builder {
        private Long id;
        private Date createdAt;
        private Date updatedAt;
        private Boolean isActive;
        private Date deletedAt;
        private String firstName;
        private String lastName;
        private String dialCode;
        private String contactNo;
        private String password;
        private String emailId;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder deletedAt(Date deletedAt) {
            this.deletedAt = deletedAt;
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

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder emailId(String emailId) {
            this.emailId = emailId;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
