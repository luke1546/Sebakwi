package com.ssafy.sebakwi.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public String test() {
        return "test!!" + LocalDateTime.now();
    }
}