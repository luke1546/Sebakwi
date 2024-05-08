package com.ssafy.sebakwi.product.controller;

import com.ssafy.sebakwi.product.service.MainService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wheels")
public class MainController {

    private final MainService mainService;

    @GetMapping(value = "/monthly/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable Long id) {
        return mainService.subscribe(id);
    }

//    @PostMapping("/monthly/{id}")
//    public void sendMonthly(@PathVariable Long id) {
//        mainService.sendMonthly(id, "data");
//    }

}
