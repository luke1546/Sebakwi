package com.ssafy.sebakwi.product.service;

import com.ssafy.sebakwi.product.domain.OhtRepository;
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

}
