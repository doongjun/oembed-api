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
    int duration;
    String description;
    String version;
    String provider_name;
    String provider_url;
    int thumbnail_height;
    int thumbnail_width;
    String thumbnail_url;
    String thumbnail_url_with_play_button;
    String html;
    String uri;
    String upload_date;
    Long video_id;
    String is_plus;
}
