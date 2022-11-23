package com.demo.project2.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BaseResource {

    @GetMapping(value = "/gateway/base")
    public ResponseEntity<String> base(){
        return ResponseEntity.ok("Gateway filter working");
    }
}
