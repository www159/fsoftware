package com.example.nationaltax.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Profile("{dev, test}")
public class TestController {
    @GetMapping("")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello test");
    }
}
