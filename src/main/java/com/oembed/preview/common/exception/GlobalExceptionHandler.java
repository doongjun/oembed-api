package com.oembed.preview.common.exception;

import com.oembed.preview.common.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotSupportedUrlException.class)
    public ResponseEntity<ErrorResponse> ExceptionHandler(NotSupportedUrlException e) {
        HttpHeaders responseHeaders = new HttpHeaders();

        ErrorResponse error = ErrorResponse.builder()
                .type(e.getHttpStatusType())
                .code(Integer.toString(e.getHttpStatusCode()))
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(error, responseHeaders, e.getHttpStatus());
    }

}
