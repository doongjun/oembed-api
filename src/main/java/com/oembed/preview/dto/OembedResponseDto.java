package com.oembed.preview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OembedResponseDto {
    String title;
    String author_name;
    String author_url;
    String type;
    int height;
    int width;
    String version;
    String providerName;
    String providerUrl;
    int thumbnailHeight;
    int thumbnailWidth;
    String thumbnailUrl;
    String html;
}
