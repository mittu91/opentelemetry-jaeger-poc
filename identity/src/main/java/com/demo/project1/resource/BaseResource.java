package com.demo.project1.resource;

import com.demo.project1.api.model.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BaseResource {

    @GetMapping(value = "/")
    public ResponseEntity<BaseResponse> baseURI() {
        return ResponseEntity.ok(BaseResponse.builder().targetURL("http://localhost:8858").build());
    }
}
