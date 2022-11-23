package com.demo.project1.service;

import com.demo.project1.api.config.CacheMap;
import com.demo.project1.api.model.response.UserSessionDTO;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Caching service to manage frequently used data
 *
 * @author Sunil Kanjar
 * @since 11th March 2020
 */
@Service
public class RedisService {
    private final RMap<String, UserSessionDTO> userSessionMap;
    private final RMap<Long, Set<String>> userSessionIdMap;
    private final RMap<Long, Collection<Long>> rolePrivilegeMap;

    @Autowired
    public RedisService(RedissonClient redissonClient) {
        userSessionMap = redissonClient.getMapCache(CacheMap.UserSessionMap.toString());
        userSessionIdMap = redissonClient.getMapCache(CacheMap.UserSessionIdMap.toString());
        rolePrivilegeMap = redissonClient.getMapCache(CacheMap.Privilege.toString());
    }

    public UserSessionDTO getUserSessionDTO(String sessionKey) {
        return userSessionMap.get(sessionKey);
    }

    public void putUserSessionDTO(UserSessionDTO userSessionDTO) {

        userSessionMap.put(userSessionDTO.getSessionId(), userSessionDTO);
        addUserSession(userSessionDTO.getUserId(), userSessionDTO.getSessionId());
    }

    public void removeUserSessionDTO(String sessionKey) {
        UserSessionDTO userSessionDTO = userSessionMap.remove(sessionKey);
        if (userSessionDTO == null) {
            return;
        }
        removeUserSession(userSessionDTO.getUserId(), userSessionDTO.getSessionId());
    }

    private void addUserSession(Long userId, String sessionKey) {
        Set<String> sessions = userSessionIdMap.get(userId);
        if (sessions == null) {
            sessions = new HashSet<>();
        }
        sessions.add(sessionKey);
        userSessionIdMap.put(userId, sessions);
    }

    private void removeUserSession(Long userId, String sessionKey) {
        Set<String> sessions = userSessionIdMap.get(userId);
        if (sessions != null && !sessions.isEmpty()) {
            sessions.remove(sessionKey);
            userSessionIdMap.put(userId, sessions);
        }
    }
    
    public void flushAllSession() {
        userSessionMap.clear();
        userSessionIdMap.clear();
    }
}
