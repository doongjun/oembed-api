package com.oembed.preview.service;

import com.oembed.preview.common.exception.NotSupportedUrlException;
import com.oembed.preview.dto.OembedResponseDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class EmbedService {

    /**
     * Oembed API 응답을 받아온다.
     * @param url
     * @return
     * @throws ParseException
     * @throws URISyntaxException
     * @throws NotSupportedUrlException
     */
    public OembedResponseDto getOembedResponse(String url) throws ParseException, URISyntaxException, NotSupportedUrlException {
        String urlHost = getUrlHost(url);
        JSONArray providers = getProviders();
        String endpointUrl = getEndpointUrl(urlHost, providers);
        String requestUrl = createRequestUrl(url, endpointUrl);

        RestTemplate restTemplate = new RestTemplate();
        OembedResponseDto oembedResponseDto = restTemplate.getForObject(requestUrl, OembedResponseDto.class);

        return oembedResponseDto;
    }

    /**
     * url의 host의 값을 가져온다.
     * @param paramUrl
     * @return
     * @throws URISyntaxException
     */
    private String getUrlHost(String paramUrl) throws URISyntaxException, NotSupportedUrlException {
        URI uri = new URI(paramUrl);
        String host = uri.getHost();
        if(host == null) {
            throw new NotSupportedUrlException(HttpStatus.BAD_REQUEST, "잘못된 URL 형식입니다.");
        }
        if (host.startsWith("www.")) {
            host = host.substring(4);
        }
        return host;
    }

    /**
     * providers.json을 받아온다.
     * @return
     * @throws ParseException
     */
    private JSONArray getProviders() throws ParseException {
        String providersUrl = "https://oembed.com/providers.json";

        RestTemplate restTemplate = new RestTemplate();
        String jsonStr = restTemplate.getForObject(providersUrl, String.class);
        return (JSONArray) new JSONParser().parse(jsonStr);
    }

    /**
     * providers의 urlHost endpoint를 가져온다.
     * @param urlHost
     * @param providers
     * @return
     */
    private String getEndpointUrl(String urlHost, JSONArray providers) throws NotSupportedUrlException {
        String endpointUrl = "";
        for(Object o : providers) {
            JSONObject jsonObject = (JSONObject) o;
            String providerUrl = jsonObject.get("provider_url").toString();

            if(providerUrl.contains(urlHost)) {
                JSONArray endpoints = (JSONArray) jsonObject.get("endpoints");
                JSONObject endpoint = (JSONObject) endpoints.get(0);
                endpointUrl = endpoint.get("url").toString();
                break;
            }
        }

        if(endpointUrl.equals("")) {
            throw new NotSupportedUrlException(HttpStatus.BAD_REQUEST, "지원하지 않는 URL 입니다.");
        }
        return endpointUrl;
    }

    /**
     * 요청 url 생성
     * @param url
     * @param endpointUrl
     * @return
     */
    private String createRequestUrl(String url, String endpointUrl) {
        if(endpointUrl.endsWith("{format}")) {
            endpointUrl = endpointUrl.substring(0, endpointUrl.lastIndexOf("{")) + "json";
        }
        return endpointUrl + "?url=" + url;
    }

}
