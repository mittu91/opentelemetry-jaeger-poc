package com.demo.project2.service;

import com.demo.project2.client.Project1URI;
import com.demo.project2.dao.model.response.UserSessionDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final RestTemplate restTemplate;
    private final String project1URI;

    public UserService(@Value("${project1.url}") String project1URI) {
        this.project1URI = project1URI;
        this.restTemplate = new RestTemplate();
    }

    public UserSessionDTO authorize(String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authToken);
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(project1URI + Project1URI.AUTHORIZE, HttpMethod.GET, entity, UserSessionDTO.class).getBody();
    }
}
