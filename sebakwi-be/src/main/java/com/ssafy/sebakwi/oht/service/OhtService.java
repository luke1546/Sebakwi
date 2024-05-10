package com.ssafy.sebakwi.oht.service;

import com.ssafy.sebakwi.oht.domain.OhtRepository;
import com.ssafy.sebakwi.oht.dto.OhtReplacementResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OhtService {

    private final OhtRepository ohtRepository;

    public OhtReplacementResponse getOhtReplacementResponse() {
        int totalOht = ohtRepository.countOht();
        int maintenance = ohtRepository.countMaintenance();

        return new OhtReplacementResponse(totalOht, maintenance);
    }
}
