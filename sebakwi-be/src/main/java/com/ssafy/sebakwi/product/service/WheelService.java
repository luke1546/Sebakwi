package com.ssafy.sebakwi.product.service;

import com.ssafy.sebakwi.product.controller.WheelController;
import com.ssafy.sebakwi.product.domain.Wheel;
import com.ssafy.sebakwi.product.domain.WheelRepository;
import com.ssafy.sebakwi.product.domain.WheelStatus;
import com.ssafy.sebakwi.product.dto.WheelMonthlyStatus;
import com.ssafy.sebakwi.product.dto.WheelMonthlyStatusResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class WheelService {

    private final WheelRepository wheelRepository;

    /**
     * 메인페이지 관련
     */

//    public WheelMonthlyStatusResponse<WheelMonthlyStatus> monthlyWheelStatusInfo() {
//        return
//    }

}
