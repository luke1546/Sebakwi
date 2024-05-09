package com.ssafy.sebakwi.util.exception;

import com.ssafy.sebakwi.util.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.ssafy.sebakwi.util.exception.CustomExceptionStatus.*;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorResponse response = ErrorResponse.builder()
            .errorCode(ex.getStatus().getErrorCode())
            .message(ex.getStatus().getMessage())
            .errorTimestamp(LocalDateTime.now())
            .build();


        return ResponseEntity.badRequest()
            .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(MethodArgumentNotValidException ex) {
        ErrorResponse response = ErrorResponse.builder()
            .errorCode(VALIDATE_INVALID.getErrorCode())
            .message(Optional.ofNullable(ex.getBindingResult().getFieldError())
                .map(FieldError::getDefaultMessage)
                .orElse(VALIDATE_INVALID.getMessage()))
            .errorTimestamp(LocalDateTime.now())
            .build();

        return ResponseEntity.badRequest()
            .body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(RuntimeException ex) {
        ErrorResponse response = ErrorResponse.builder()
            .errorCode(SERVER_ERROR.getErrorCode())
            .message(SERVER_ERROR.getMessage())
            .build();

        return ResponseEntity.badRequest()
            .body(response);
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateDataException(RuntimeException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(NO_CHANGE_STATUS.getErrorCode())
                .message(NO_CHANGE_STATUS.getMessage())
                .build();

        return ResponseEntity.badRequest()
                .body(response);
    }

}
