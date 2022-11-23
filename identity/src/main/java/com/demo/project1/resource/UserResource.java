package com.demo.project1.resource;

import com.demo.project1.api.config.JWTUtils;
import com.demo.project1.api.constant.Project1URI;
import com.demo.project1.api.model.request.CreateUserRequest;
import com.demo.project1.api.model.request.LoginUserRequest;
import com.demo.project1.api.model.request.ResetPasswordRequest;
import com.demo.project1.api.model.response.LoginResponseDTO;
import com.demo.project1.api.model.response.UserResponse;
import com.demo.project1.api.model.response.UserSessionDTO;
import com.demo.project1.dao.entity.UserSession;
import com.demo.project1.service.RedisService;
import com.demo.project1.service.UserService;
import com.demo.project1.service.UserSessionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserResource {

    private final UserService userService;
    private final JWTUtils jwtUtils;
    private final UserSessionService userSessionService;
    private final ObjectMapper objectMapper;
    private final RedisService redisService;

    public UserResource(UserService userService, JWTUtils jwtUtils, UserSessionService userSessionService, ObjectMapper objectMapper, RedisService redisService) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.userSessionService = userSessionService;
        this.objectMapper = objectMapper;
        this.redisService = redisService;
    }

    @PostMapping(Project1URI.DEMO_USER)
    public ResponseEntity<UserResponse> saveUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.createUser(createUserRequest));
    }

    @GetMapping(Project1URI.DEMO_USER_BY_ID)
    public ResponseEntity<UserResponse> getUser(@PathVariable(name = "id") Long userId) throws JsonProcessingException {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PostMapping(value = Project1URI.LOGIN)
    ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginUserRequest loginUserRequest) {
        return ResponseEntity.ok(userService.loginUser(loginUserRequest));
    }

    @PostMapping(Project1URI.RESET_PASSWORD)
    ResponseEntity<String> resetPassword(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestBody ResetPasswordRequest passwordRequest) {
        userService.resetPassword(passwordRequest, jwtUtils.getUserIdFromToken(authorization));
        return ResponseEntity.ok("Password changed");
    }

    @PostMapping(Project1URI.LOG_OUT)
    ResponseEntity<String> logOut(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        userService.logOut(getUserSessionDTO(authorization));
        return ResponseEntity.ok("Log out successful");
    }

    private UserSessionDTO getUserSessionDTO(String auth) {
        String sessionId = jwtUtils.getSessionIdFromToken(auth);
        UserSessionDTO userSessionDTO = redisService.getUserSessionDTO(sessionId);
        if (userSessionDTO != null) {
            return userSessionDTO;
        }

        UserSession userSession = userSessionService.getBySessionId(sessionId);
        return objectMapper.convertValue(userSession, UserSessionDTO.class);
    }

}
