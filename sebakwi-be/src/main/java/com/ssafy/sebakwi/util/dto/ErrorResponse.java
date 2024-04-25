package com.ssafy.sebakwi.util.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private Integer errorCode;
    private String message;
    private LocalDateTime errorTimestamp;
}

