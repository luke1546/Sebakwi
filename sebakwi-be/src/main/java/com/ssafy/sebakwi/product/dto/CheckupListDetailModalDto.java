package com.ssafy.sebakwi.product.dto;

import com.ssafy.sebakwi.product.domain.CheckupList;
import com.ssafy.sebakwi.product.domain.Wheel;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckupListDetailModalDto {

    private Wheel wheel;
    private CheckupList checkupList;
}
