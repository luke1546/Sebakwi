package com.ssafy.sebakwi.checkupList.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckupListDetailModalResponse {

    private CheckupListDetailModalWheel checkupListDetailModalWheel;
}
