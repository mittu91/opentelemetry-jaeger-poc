package com.demo.project2.security;

import com.demo.project2.dao.model.response.UserSessionDTO;
import com.demo.project2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
public class GatewayAuthenticationManager implements ReactiveAuthenticationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayAuthenticationManager.class);
    private final UserService userService;

    @Autowired
    public GatewayAuthenticationManager(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        try {
            UserSessionDTO userSessionDTO = userService.authorize(authToken);
            if (userSessionDTO != null) {
//                List<String> permissions = userSessionDTO.getPrivileges().stream().map(p -> Long.toString(p)).collect(Collectors.toList());
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        String.valueOf(userSessionDTO.getUserId()),
                        userSessionDTO.getSessionId(),
//                        permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                        new ArrayList<>());
                return Mono.just(auth);
            } else {
                LOGGER.error("Unauthorized access " + authentication.getName());
                return Mono.empty();
            }
        } catch (Exception e) {
            LOGGER.error("Unauthorized access " + authentication.getName(), e);
            return Mono.empty();
        }
    }
}
