package com.ssafy.sebakwi.wheel.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WheelMonthlyStatus {

    private String wheelNumber;
    private String ohtNumber;
    private int position;
    private double diameter;
    private boolean crack;
    private boolean stamp;
    private boolean abrasion;

}
