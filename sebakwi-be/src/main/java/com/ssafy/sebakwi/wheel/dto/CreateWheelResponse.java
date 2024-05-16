package com.ssafy.sebakwi.wheel.dto;

import lombok.Data;

@Data
public class CreateWheelResponse {
    private String serialNumber;

    public CreateWheelResponse(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}