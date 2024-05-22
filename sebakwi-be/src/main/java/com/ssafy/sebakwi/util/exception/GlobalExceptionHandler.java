package com.ssafy.sebakwi.util.exception;

import com.ssafy.sebakwi.util.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.ssafy.sebakwi.util.exception.CustomExceptionStatus.*;


@RestControllerAdvice
@Slf4j
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
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(9002)
                .message(ex.getMessage())
                .errorTimestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(500).body(errorResponse);
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
