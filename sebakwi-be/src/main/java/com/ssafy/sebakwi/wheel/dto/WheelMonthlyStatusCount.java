package com.ssafy.sebakwi.wheel.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WheelMonthlyStatusCount {

    private int crack;
    private int stamp;
    private int abrasion;
    private int total;


    public void updateCrack(int num) {
        this.crack += num;
        this.total += num;
    }

    public void updateStamp(int num) {
        this.stamp += num;
        this.total += num;
    }

    public void updateAbrasion(int num) {
        this.abrasion += num;
        this.total += num;
    }

}
