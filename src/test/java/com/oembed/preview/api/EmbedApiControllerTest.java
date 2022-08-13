package com.oembed.preview.api;

import com.oembed.preview.common.ErrorResponse;
import com.oembed.preview.dto.OembedResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmbedApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void YouTube_Oembed_얻어온다() throws Exception {
        //given
        String link = "https://www.youtube.com/watch?v=dBD54EZIrZo";
        String url = "http://localhost:" + port + "/api/oembed?url=" + link;

        //when
        ResponseEntity<OembedResponseDto> responseEntity = restTemplate
                .getForEntity(url, OembedResponseDto.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        OembedResponseDto responseBody = responseEntity.getBody();
        assertThat(responseBody.getType()).isEqualTo("video");
        assertThat(responseBody.getProvider_name()).isEqualTo("YouTube");
        assertThat(responseBody.getProvider_url()).isEqualTo("https://www.youtube.com/");
    }

    @Test
    public void Twitter_Oembed_얻어온다() throws Exception {
        //given
        String link = "https://twitter.com/hellopolicy/status/867177144815804416";
        String url = "http://localhost:" + port + "/api/oembed?url=" + link;

        //when
        ResponseEntity<OembedResponseDto> responseEntity = restTemplate
                .getForEntity(url, OembedResponseDto.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        OembedResponseDto responseBody = responseEntity.getBody();
        assertThat(responseBody.getType()).isEqualTo("rich");
        assertThat(responseBody.getProvider_name()).isEqualTo("Twitter");
        assertThat(responseBody.getProvider_url()).isEqualTo("https://twitter.com");
    }

    @Test
    public void Vimeo_Oembed_얻어온다() throws Exception {
        //given
        String link = "https://vimeo.com/20097015";
        String url = "http://localhost:" + port + "/api/oembed?url=" + link;

        //when
        ResponseEntity<OembedResponseDto> responseEntity = restTemplate
                .getForEntity(url, OembedResponseDto.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        OembedResponseDto responseBody = responseEntity.getBody();
        assertThat(responseBody.getType()).isEqualTo("video");
        assertThat(responseBody.getProvider_name()).isEqualTo("Vimeo");
        assertThat(responseBody.getProvider_url()).isEqualTo("https://vimeo.com/");
    }

    @Test
    public void 지원되지않는_URL_예외() throws Exception {
        //given
        String link = "https://github.com/doongjun";
        String url = "http://localhost:" + port + "/api/oembed?url=" + link;

        //when
        ResponseEntity<ErrorResponse> responseEntity = restTemplate
                .getForEntity(url, ErrorResponse.class);

        //then
        ErrorResponse error = responseEntity.getBody();
        assertThat(error.getType()).isEqualTo("Bad Request");
        assertThat(error.getCode()).isEqualTo("400");
        assertThat(error.getMessage()).isEqualTo("지원하지 않는 URL 입니다.");
    }
}
