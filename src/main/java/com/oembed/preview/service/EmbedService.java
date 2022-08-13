package com.oembed.preview.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmbedService {

    public Map<String, Object> getOembedResponse(String url) throws ParseException, URISyntaxException {
        String urlHost = getUrlHost(url);
        JSONArray providers = getProviders();
        String endpointUrl = getEndpointUrl(urlHost, providers);
        String requestUrl = createRequestUrl(url, endpointUrl);

        Map<String, Object> map = new HashMap<>();
        return map;
    }

    /**
     * url의 host의 값을 가져온다.
     * @param paramUrl
     * @return
     * @throws URISyntaxException
     */
    private String getUrlHost(String paramUrl) throws URISyntaxException {
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
    private JSONArray getProviders() throws ParseException {
        String providersUrl = "https://oembed.com/providers.json";

        RestTemplate restTemplate = new RestTemplate();
        String jsonStr = restTemplate.getForObject(providersUrl, String.class);
        return (JSONArray) new JSONParser().parse(jsonStr);
    }

    /**
     * providers의 urlHost endpoint를 가져온다..
     * @param urlHost
     * @param providers
     * @return
     */
    private String getEndpointUrl(String urlHost, JSONArray providers) {
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
        return endpointUrl;
    }

    private String createRequestUrl(String url, String endpointUrl) {
        if(endpointUrl.endsWith("{format}")) {
            endpointUrl = endpointUrl.substring(0, endpointUrl.lastIndexOf("{")) + "json";
        }
        return endpointUrl + "?url=" + url;
    }

}
