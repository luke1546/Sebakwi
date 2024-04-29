package com.ssafy.sebakwi.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum CustomExceptionStatus {

    // 서비스로직상 예외 처리
    PRODUCT_INVALID(1000, "조회한 상품이 없습니다."),


    // 500 Internal Server Error 일괄 처리
    SEVER_ERROR(9000, "서버오류 관리자에게 문의하세요"),
    
    // @Validated -> MethodArgumentNotValidException 예외 일괄 처리
    VALIDATE_INVALID(9001, "요청한값이 올바르지않습니다.");

    private final Integer errorCode;
    private final String message;
}
