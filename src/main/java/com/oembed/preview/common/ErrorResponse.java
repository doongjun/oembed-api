package com.oembed.preview.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private String type;
    private String code;
    private String message;
}
