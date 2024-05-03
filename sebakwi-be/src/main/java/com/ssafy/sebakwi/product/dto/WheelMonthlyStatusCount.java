package com.ssafy.sebakwi.product.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WheelMonthlyStatusCount {

    private int crack;
    private int stamp;
    private int peeling;
    private int total;
}
