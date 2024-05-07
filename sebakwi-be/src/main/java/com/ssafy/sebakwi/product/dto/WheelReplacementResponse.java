package com.ssafy.sebakwi.product.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WheelReplacementResponse {
    private String wheelNumber;
    private LocalDate createdDate;
}
