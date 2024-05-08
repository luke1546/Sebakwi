package com.ssafy.sebakwi.product.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckupListDetailModalResponse {

    private CheckupListDetailModalWheel checkupListDetailModalWheel;
    private CheckupListDetailModalWheelNumberList checkupListDetailModalWheelNumberList;
}
