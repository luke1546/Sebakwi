package com.ssafy.sebakwi.jetson.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JetsonButtonResponse {

    private int camera;
    private boolean power;
}
