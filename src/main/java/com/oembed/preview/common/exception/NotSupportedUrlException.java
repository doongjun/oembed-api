package com.oembed.preview.common.exception;

import org.springframework.http.HttpStatus;

public class NotSupportedUrlException extends Exception{

    private HttpStatus httpStatus;

    public NotSupportedUrlException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

    public String getHttpStatusType() {
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
