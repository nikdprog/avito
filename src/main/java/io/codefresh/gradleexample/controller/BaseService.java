package io.codefresh.gradleexample.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseService {

    @GetMapping("/api/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("ok");
    }
}
