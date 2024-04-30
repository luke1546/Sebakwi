package com.ssafy.sebakwi.product.controller;


import com.ssafy.sebakwi.product.domain.OhtRepository;
import com.ssafy.sebakwi.util.dto.OhtReplacementResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OhtController {

    private final OhtRepository ohtRepository;

    @GetMapping("/api/oht/replacement")
    public OhtReplacementResponse OhtReplacement() {
        int totalOht = ohtRepository.countOht();
        int maintenance = ohtRepository.countMaintenance();

        return new OhtReplacementResponse(totalOht, maintenance);
    }

    @GetMapping("/api/oht/count")
    public int OhtCount() {
        return ohtRepository.countOht();
    }
}
