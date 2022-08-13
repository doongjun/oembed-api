package com.oembed.preview.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OembedResponseDto {
    String title;
    String author_name;
    String author_url;
    String type;
    int height;
    int width;
    String version;
    String provider_name;
    String provider_url;
    int thumbnail_height;
    int thumbnail_width;
    String thumbnail_url;
    String html;
}
