package com.ssafy.sebakwi.checkupList.dto;

import lombok.*;

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
    }

}
