package com.ssafy.sebakwi.product.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WheelMonthlyStatusResponse<T> {

    private WheelMonthlyStatusCount count;
    private List<T> wheelList;

    public WheelMonthlyStatusResponse(int totalPages, List<T> checkupListArray) {

    }
}
