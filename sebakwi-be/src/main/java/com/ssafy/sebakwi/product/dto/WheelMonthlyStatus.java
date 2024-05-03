package com.ssafy.sebakwi.product.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WheelMonthlyStatus {

    private String wheelNumber;
    private String ohtNumber;
    private int position;
    private boolean crack;
    private boolean stamp;
    private boolean peeling;

}
