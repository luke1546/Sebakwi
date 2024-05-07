package com.ssafy.sebakwi.util.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {

    private Integer errorCode;
    private String message;
    private LocalDateTime errorTimestamp;
}

