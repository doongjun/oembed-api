package com.oembed.preview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OembedResponseDto {
    String result;
    Object response;
}
