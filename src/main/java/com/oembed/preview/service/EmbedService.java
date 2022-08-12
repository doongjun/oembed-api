package com.oembed.preview.service;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class EmbedService {

    /**
     * url의 host의 값을 가져온다.
     * @param paramUrl
     * @return
     * @throws URISyntaxException
     */
    public String getUrlHost(String paramUrl) throws URISyntaxException {
        URI uri = new URI(paramUrl);
        String host = uri.getHost();
        if (host.startsWith("www.")) {
            host = host.substring(4);
        }
        return host;
    }

    /**
     * providers.json을 읽어온다.
     * @return
     * @throws ParseException
     */
    private static JSONArray getProviders() throws ParseException {
        String providersUrl = "https://oembed.com/providers.json";

        RestTemplate restTemplate = new RestTemplate();
        String jsonStr = restTemplate.getForObject(providersUrl, String.class);
        return (JSONArray) new JSONParser().parse(jsonStr);
    }

}
