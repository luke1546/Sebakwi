package com.ssafy.sebakwi.checkupList.dto;

import com.ssafy.sebakwi.checkupList.domain.CheckupList;
import com.ssafy.sebakwi.wheel.domain.Wheel;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckupListDetailModalDto {

    private Wheel wheel;
    private CheckupList checkupList;
}
