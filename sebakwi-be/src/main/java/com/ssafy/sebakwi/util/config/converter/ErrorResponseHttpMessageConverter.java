package com.ssafy.sebakwi.util.config.converter;

import com.ssafy.sebakwi.util.dto.ErrorResponse;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ErrorResponseHttpMessageConverter extends AbstractHttpMessageConverter<ErrorResponse> {

    public ErrorResponseHttpMessageConverter() {
        super(MediaType.TEXT_EVENT_STREAM);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return ErrorResponse.class.isAssignableFrom(clazz);
    }

    @Override
    protected ErrorResponse readInternal(Class<? extends ErrorResponse> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        throw new UnsupportedOperationException("Reading ErrorResponse is not supported");
    }

    @Override
    protected void writeInternal(ErrorResponse errorResponse, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String data = "data: " + errorResponse.toJson() + "\n\n";
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        outputMessage.getBody().write(bytes);
    }


}
