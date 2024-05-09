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

    public void updateCrack(int num) {
        this.crack += num;
        this.total += num;
    }

    public void updateStamp(int num) {
        this.stamp += num;
        this.total += num;
    }

    public void updatePeeling(int num) {
        this.peeling += num;
        this.total += num;
    }

}
