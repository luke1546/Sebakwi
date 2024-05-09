package com.ssafy.sebakwi.util.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class BasicResponse {
    private int count;
    private List<?> result;
}