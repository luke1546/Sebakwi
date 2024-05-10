package com.ssafy.sebakwi.product.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateWheelRequest {

    @NotEmpty
    private String ohtSerialNumber;
    @NotEmpty
    private String wheelSerialNumber;
    private int position;

    private String wheelImage;
    private float diameter;
    private boolean crack;
    private boolean stamp;
    private boolean peeling;
}