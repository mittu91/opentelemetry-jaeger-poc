package com.demo.project1.dao.repository;

import com.demo.project1.dao.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

    UserSession findBySessionIdAndActive(String sessionId, Boolean active);
    
}
