package com.ssafy.sebakwi.sse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private final String message;
}
