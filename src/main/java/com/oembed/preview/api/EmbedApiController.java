package com.oembed.preview.api;

import com.oembed.preview.dto.OembedResponseDto;
import com.oembed.preview.service.EmbedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EmbedApiController {

    private final EmbedService embedService;

    @GetMapping("/api/oembed")
    public ResponseEntity requestHandler(@RequestParam String url) {
        
        // TODO : EmbedService 호출
        
        return ResponseEntity.badRequest()
                .body(new OembedResponseDto("fail", "지원하지 않는 URL 입니다."));
    }

 }

