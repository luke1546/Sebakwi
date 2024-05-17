package com.ssafy.sebakwi.wheel.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CreateWheelRequest {

    private String ohtSerialNumber;
    @NotEmpty
    private String wheelSerialNumber;
    private int position;

    private String wheelImage;
    private double diameter;
    private boolean crack;
    private boolean stamp;
}