package com.ssafy.sebakwi.product.dto;

import com.ssafy.sebakwi.product.domain.WheelStatus;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckupListDetailModalWheelNumberList {

    private WheelNumberStatus FL;
    private WheelNumberStatus FR;
    private WheelNumberStatus RL;
    private WheelNumberStatus RR;

    @Getter
    @AllArgsConstructor
    public static class WheelNumberStatus {
        private String wheelNumber;
        private WheelStatus currentStatus;
    }

}
