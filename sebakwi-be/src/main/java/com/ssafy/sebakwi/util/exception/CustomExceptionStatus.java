package com.ssafy.sebakwi.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum CustomExceptionStatus {

    // 서비스로직상 예외 처리
//    PRODUCT_INVALID(1000, "조회한 상품이 없습니다."),

    // checkupList 조회했을 때 하나도 안뜨는 경우
    CHECKUPLIST_NOT_FOUND(1000, "NotFound"),

    // 이미 검사된 바퀴를 검사했는데 상태변화가 없는 경우
    NO_CHANGE_STATUS(1001, "No Change in status"),


    // 500 Internal Server Error 일괄 처리
    SERVER_ERROR(9000, "서버오류 관리자에게 문의하세요"),
    
    // @Validated -> MethodArgumentNotValidException 예외 일괄 처리
    VALIDATE_INVALID(9001, "요청한값이 올바르지않습니다.");

    private final Integer errorCode;
    private final String message;
}
