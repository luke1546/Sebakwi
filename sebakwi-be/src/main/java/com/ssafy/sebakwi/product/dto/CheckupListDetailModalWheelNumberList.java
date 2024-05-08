package com.ssafy.sebakwi.product.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckupListDetailModalWheelNumberList {

    private String LF;
    private String RF;
    private String LR;
    private String RR;

}
