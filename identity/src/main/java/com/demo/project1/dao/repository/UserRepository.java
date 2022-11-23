package com.demo.project1.dao.repository;

import com.demo.project1.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByIdAndIsActiveAndDeletedAtIsNull(Long userId, boolean isActive);

    User findByEmailIdAndIsActiveAndDeletedAtIsNull(String emailId, boolean isActive);

    User findByEmailIdAndDeletedAtIsNull(String emailId);

    User findByDialCodeAndContactNoAndDeletedAtIsNull(String dialCode, String contactNo);
}
