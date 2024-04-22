package com.ssafy.sebakwi.util.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class BasicResponse {
    private int count;
    private List<?> result;
}