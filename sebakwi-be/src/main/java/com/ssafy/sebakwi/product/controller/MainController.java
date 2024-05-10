package com.ssafy.sebakwi.product.controller;

import com.ssafy.sebakwi.product.service.MainService;
import com.ssafy.sebakwi.product.service.WheelService;
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

    @GetMapping(value = "/monthly/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe() {
        return mainService.subscribe();
    }

}
