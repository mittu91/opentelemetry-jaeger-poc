package com.demo.project1.service;

import com.demo.project1.api.config.JWTUtils;
import com.demo.project1.api.model.request.CreateUserRequest;
import com.demo.project1.api.model.request.LoginUserRequest;
import com.demo.project1.api.model.request.ResetPasswordRequest;
import com.demo.project1.api.model.request.UserDetails;
import com.demo.project1.api.model.response.LoginResponseDTO;
import com.demo.project1.api.model.response.UserResponse;
import com.demo.project1.api.model.response.UserSessionDTO;
import com.demo.project1.dao.entity.User;
import com.demo.project1.dao.entity.UserSession;
import com.demo.project1.dao.repository.UserRepository;
import com.demo.project1.exception.InvalidCredentialsException;
import com.demo.project1.exception.InvalidSessionException;
import com.demo.project1.exception.UserExistsException;
import com.demo.project1.exception.UserNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final UserSessionService userSessionService;
    private final JWTUtils jwtUtils;
    private final SCryptPasswordEncoder passwordEncoder;
    private final RedisService redisService;
    private final RestTemplate restTemplate;

    public UserService(UserRepository userRepository, ObjectMapper objectMapper, UserSessionService userSessionService, JWTUtils jwtUtils, SCryptPasswordEncoder passwordEncoder, RedisService redisService, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.userSessionService = userSessionService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.redisService = redisService;
        this.restTemplate = restTemplate;
    }


    public LoginResponseDTO loginUser(LoginUserRequest loginUserRequest) {
        User user = userRepository.findByEmailIdAndIsActiveAndDeletedAtIsNull(loginUserRequest.getEmail(), true);
        if (user == null) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }

        if (!passwordEncoder.matches(loginUserRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }
        UserSession userSession = userSessionService.createUserSession(user);
        UserDetails userDetails = UserDetails.builder()
                .userId(user.getId())
                .sessionId(userSession.getSessionId())
                .build();

        UserSessionDTO userSessionDTO = objectMapper.convertValue(userSession, UserSessionDTO.class);

        redisService.putUserSessionDTO(userSessionDTO);
        LoginResponseDTO loginResponseDTO = objectMapper.convertValue(user, LoginResponseDTO.class);
        loginResponseDTO = LoginResponseDTO.newBuilder(loginResponseDTO)
                .sessionId(userSession.getSessionId())
                .refreshToken(jwtUtils.generateRefreshToken(userDetails, false))
                .accessToken(jwtUtils.generateToken(userDetails, true))
                .loginDate(new Date())
                .build();

        return loginResponseDTO;
    }

    public void resetPassword(ResetPasswordRequest resetPasswordRequest, Long userId) {
        User user = userRepository.findByIdAndIsActiveAndDeletedAtIsNull(userId, true);
        if (user == null || !passwordEncoder.matches(resetPasswordRequest.getOldPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }

        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
        userRepository.save(user);
    }

    public void logOut(UserSessionDTO userSessionDTO) {
        if (userSessionDTO == null || userSessionDTO.getUserId() == null) {
            throw new InvalidSessionException("Invalid session.");
        }
        User user = userRepository.findByIdAndIsActiveAndDeletedAtIsNull(userSessionDTO.getUserId(), true);
        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }

        userSessionService.logOutBySessionId(userSessionDTO.getSessionId());
        redisService.removeUserSessionDTO(userSessionDTO.getSessionId());
    }

    public UserResponse createUser(CreateUserRequest createUserRequest) {
        User user = userRepository.findByEmailIdAndDeletedAtIsNull(createUserRequest.getEmailId());
        if (user != null) {
            throw new UserExistsException();
        }

        if (StringUtils.hasText(createUserRequest.getDialCode()) && StringUtils.hasText(createUserRequest.getContactNo())) {
            user = userRepository.findByDialCodeAndContactNoAndDeletedAtIsNull(createUserRequest.getDialCode(), createUserRequest.getContactNo());
            if (user != null) {
                throw new UserExistsException();
            }
        }

        user = objectMapper.convertValue(createUserRequest, User.class);
        user = userRepository.save(User.newBuilder(user)
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .isActive(true)
                .build());
        return objectMapper.convertValue(user, UserResponse.class);
    }

    public UserResponse getUser(Long userId) throws JsonProcessingException {
        User user = userRepository.findByIdAndIsActiveAndDeletedAtIsNull(userId, true);
        ResponseEntity<Object> quarkusCall = restTemplate.getForEntity("http://localhost:8080/qurkusApi/getExpert", Object.class);
        ResponseEntity<Map> thirdParty = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/todos/1", Map.class);
        String resourceUrl
                = "http://localhost:3000/node/api/hello";
        ResponseEntity<String> response
                = restTemplate.getForEntity(resourceUrl, String.class);
        logger.info("node response: " + response.getBody() + new ObjectMapper().writeValueAsString(response.getBody()));
        logger.info(Objects.requireNonNull(thirdParty.getBody()).toString());
        logger.info("node response: " + quarkusCall.getBody() + new ObjectMapper().writeValueAsString(quarkusCall.getBody()));

        return objectMapper.convertValue(user, UserResponse.class);
    }
    @PostConstruct
    public UserResponse createDemoUser(){
        User user = userRepository.findByEmailIdAndIsActiveAndDeletedAtIsNull("admin@gmail.com",true);
        if (user ==null) {
            user= User.newBuilder().firstName("admin").lastName("admin").emailId("admin@gmail.com").dialCode("1234")
                    .isActive(true).updatedAt(new Date()).contactNo("9565656")
                    .createdAt(new Date()).
                    build();
            userRepository.save(user);
        }
        return objectMapper.convertValue(user, UserResponse.class);
    }
}
