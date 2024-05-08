package com.ssafy.sebakwi.product.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WheelMonthlyStatusResponse<T> {

    private WheelMonthlyStatusCount count;

    @Builder.Default
    private List<T> wheelList = null;

    public void updateWheelList(List<T> wheelList) {
        this.wheelList = wheelList != null ? wheelList : new ArrayList<>();
    }

}
