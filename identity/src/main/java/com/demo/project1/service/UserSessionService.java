package com.demo.project1.service;

import com.demo.project1.api.config.RandomUtils;
import com.demo.project1.dao.entity.User;
import com.demo.project1.dao.entity.UserSession;
import com.demo.project1.dao.repository.UserSessionRepository;
import com.demo.project1.exception.InvalidCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserSessionService {

    private final UserSessionRepository userSessionRepository;
    private static final int SESSION_ID_SIZE = 32;

    public UserSessionService(UserSessionRepository userSessionRepository) {
        this.userSessionRepository = userSessionRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserSession createUserSession(User userDetails) {
        return userSessionRepository.save(UserSession.builder()
                .sessionId(RandomUtils.generateAlphaNumericString(SESSION_ID_SIZE))
                .userId(userDetails.getId())
                .loginDate(new Date())
                .lastAccessDate(new Date())
                .active(true)
                .build());
    }

    public UserSession getBySessionId(String sessionId) {
        return userSessionRepository.findBySessionIdAndActive(sessionId, true);
    }

    public void logOutBySessionId(String sessionId) {
        UserSession session = userSessionRepository.findBySessionIdAndActive(sessionId, true);
        if (session == null) {
            throw new InvalidCredentialsException("Session not found in given token");
        }
        session.setActive(false);
        session.setLogOutDate(new Date());
        session.setLastAccessDate(new Date());
        userSessionRepository.save(session);
//        userSessionService.delete(sessionId);
    }
}
