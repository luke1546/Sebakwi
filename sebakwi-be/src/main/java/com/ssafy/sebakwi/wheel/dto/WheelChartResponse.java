package com.ssafy.sebakwi.wheel.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WheelChartResponse {

    private List<String> xData;
    private List<Integer> yData;
    private List<List<WheelMonthlyStatus>> toolTips;
}
