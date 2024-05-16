package com.ssafy.sebakwi.oht.controller;


import com.ssafy.sebakwi.oht.domain.OhtRepository;
import com.ssafy.sebakwi.oht.dto.OhtReplacementResponse;
import com.ssafy.sebakwi.oht.service.OhtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OhtController {

    private final OhtRepository ohtRepository;
    private final OhtService ohtService;

    @GetMapping("/api/oht/replacement")
    public OhtReplacementResponse OhtReplacement() {
        return ohtService.getOhtReplacementResponse();
    }



    @GetMapping("/api/oht/count")
    public int OhtCount() {
        return ohtRepository.countOht();
    }
}
