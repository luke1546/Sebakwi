package com.ssafy.sebakwi.util.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {

    private Integer errorCode;
    private String message;
    private LocalDateTime errorTimestamp;

    @JsonValue
    public String toJson() {
        return this.toString(); // 또는 Jackson 객체 매퍼를 사용하여 JSON 문자열을 생성할 수 있습니다.
    }
}

