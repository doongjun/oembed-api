package com.oembed.preview.api;

import com.oembed.preview.common.exception.NotSupportedUrlException;
import com.oembed.preview.service.EmbedService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
public class EmbedApiController {

    private final EmbedService embedService;

    @GetMapping("/api/oembed")
    public ResponseEntity getOembedResponse(@RequestParam String url) throws ParseException, URISyntaxException, NotSupportedUrlException {
        return ResponseEntity.ok().body(embedService.getOembedResponse(url));
    }

 }

