package io.codefresh.gradleexample.controller;



import org.hibernate.SessionFactory;
import org.hibernate.cache.spi.CacheImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseService {




    @GetMapping("/api/ping")
    public ResponseEntity<String> ping() {
        //cacheService.clearCache();
        return ResponseEntity.ok("ok");
    }
}
