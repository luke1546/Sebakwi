package com.ssafy.sebakwi.product.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
