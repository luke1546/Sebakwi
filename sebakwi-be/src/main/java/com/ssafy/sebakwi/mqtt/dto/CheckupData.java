package com.ssafy.sebakwi.mqtt.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CheckupData {

    @NotEmpty
    private String ohtSerialNumber;

    @NotEmpty
    private String wheelSerialNumber;

    private int position;

    private String wheelImage;

    private double diameter;

    private boolean crack;

    private boolean stamp;

    private boolean abrasion;
}