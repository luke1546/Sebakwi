package com.ssafy.sebakwi.jetson.controller;

import com.ssafy.sebakwi.jetson.dto.JetsonButtonRequest;
import com.ssafy.sebakwi.jetson.dto.JetsonButtonResponse;
import com.ssafy.sebakwi.jetson.service.JetsonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/jetson")
public class JetsonController {

    private final JetsonService jetsonService;

    @GetMapping("/status")
    public List<Boolean> showJetsonStatus() {
        return jetsonService.getJetsonStatus();
    }

    @PostMapping("/button")
    public JetsonButtonResponse clickJetsonButton(@RequestBody @Valid JetsonButtonRequest request) {
        return jetsonService.clickJetsonButton(request);
    }
}
