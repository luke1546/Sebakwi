package com.ssafy.sebakwi.sse.controller;

import com.ssafy.sebakwi.sse.service.SseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wheels")
public class SseController {

    private final SseService sseService;

    @GetMapping(value = "/monthly/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe() {
        return sseService.subscribe();
    }

}
