package com.demo.project1.resource;

import com.demo.project1.api.config.JWTUtils;
import com.demo.project1.api.constant.Project1URI;
import com.demo.project1.api.model.response.UserSessionDTO;
import com.demo.project1.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationResource {

    private final AuthenticationService authenticationService;
    private final JWTUtils jwtUtils;

    @Autowired
    public AuthenticationResource(AuthenticationService authenticationService, JWTUtils jwtUtils) {
        this.authenticationService = authenticationService;
        this.jwtUtils = jwtUtils;
    }

   
    @GetMapping(Project1URI.AUTHORIZE)
    ResponseEntity<UserSessionDTO> authorize(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        return ResponseEntity.ok(authenticationService.authorize(JWTUtils.extractToken(authorization)));
    }

}
