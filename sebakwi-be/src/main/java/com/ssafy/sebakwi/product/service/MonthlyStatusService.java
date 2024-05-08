package com.ssafy.sebakwi.product.service;

import com.ssafy.sebakwi.product.dto.WheelMonthlyStatus;
import com.ssafy.sebakwi.product.dto.WheelMonthlyStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class MonthlyStatusService {

    private final WheelService wheelService;

    public MonthlyStatusService(WheelService wheelService) {
        this.wheelService = wheelService;
    }

    public WheelMonthlyStatusResponse<WheelMonthlyStatus> getDefaultMonthlyStatus() {
        return wheelService.monthlyWheelStatusInfo();
    }
}
