package com.demo.project1.service;

import com.demo.project1.api.config.ClaimKeys;
import com.demo.project1.api.config.JWTUtils;
import com.demo.project1.api.model.response.UserSessionDTO;
import com.demo.project1.dao.entity.User;
import com.demo.project1.dao.entity.UserSession;
import com.demo.project1.dao.repository.UserRepository;
import com.demo.project1.exception.InvalidAuthTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserSessionService userSessionService;
    private final JWTUtils jwtUtils;
    private final SCryptPasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    @Autowired
    public AuthenticationService(UserRepository userRepository, UserSessionService userSessionService, JWTUtils jwtUtils, SCryptPasswordEncoder passwordEncoder, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.userSessionService = userSessionService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = objectMapper;
    }

    public UserSessionDTO authorize(String auth) {

        Claims claims = jwtUtils.getAllClaimsFromToken(auth);
        if (claims.get(ClaimKeys.SESSION_ID) == null || jwtUtils.isTokenExpired(auth)) {
            throw new InvalidAuthTokenException("Invalid authorization token.");
        }

        User user = userRepository.findByIdAndIsActiveAndDeletedAtIsNull(Long.valueOf(claims.getSubject()), true);
        if (user == null || !user.getActive() || user.getDeletedAt() != null) {
            throw new InvalidAuthTokenException("Invalid authorization token.");
        }

        UserSession userSession = userSessionService.getBySessionId((String) claims.get(ClaimKeys.SESSION_ID));

        if (userSession == null) {
            throw new InvalidAuthTokenException("Invalid authorization token.");
        }
        return objectMapper.convertValue(userSession, UserSessionDTO.class);
    }
}
