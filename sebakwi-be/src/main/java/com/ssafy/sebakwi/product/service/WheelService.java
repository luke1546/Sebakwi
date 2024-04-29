package com.ssafy.sebakwi.product.service;

import com.ssafy.sebakwi.product.domain.Wheel;
import com.ssafy.sebakwi.product.domain.WheelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WheelService {

    private final WheelRepository wheelRepository;

    public Wheel findByWheelSerialNumber(String wheelSerialNumber) {
        return null;
    }


//    @Transactional
//    public void create(Wheel wheel) {
//        wheelRepository.save(wheel);
//
//    }
}
