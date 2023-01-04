package com.example.hotdealnoti.common.util.coupang;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


@Component
@RequiredArgsConstructor
@Slf4j
public class CoupangPartnersLinkGenerator {

    private final ObjectMapper objectMapper;

    private final static String REQUEST_METHOD = "POST";
    private final static String DOMAIN = "https://api-gateway.coupang.com";
    private final static String URL = "/v2/providers/affiliate_open_api/apis/openapi/v1/deeplink";

    @Value("${coupang-parters.access-key}")
    private String ACCESS_KEY;

    @Value("${coupang-parters.secret-key}")
    private String SECRET_KEY;


    public String generateLink(String originalUrl) throws IOException {

        String REQUEST_JSON = String.format("{\"coupangUrls\": [\"%s\"]}",originalUrl);
        // Generate HMAC string
        String authorization = HmacGenerator.generate(REQUEST_METHOD, URL, SECRET_KEY, ACCESS_KEY);

        // Send request
        StringEntity entity = new StringEntity(REQUEST_JSON, "UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");

        org.apache.http.HttpHost host = org.apache.http.HttpHost.create(DOMAIN);
        org.apache.http.HttpRequest request = org.apache.http.client.methods.RequestBuilder
                .post(URL).setEntity(entity)
                .addHeader("Authorization", authorization)
                .build();

        org.apache.http.HttpResponse httpResponse = org.apache.http.impl.client.HttpClientBuilder.create().build().execute(host, request);
        PartnersLinkResponse partnersLinkResponse = objectMapper.readValue(httpResponse.getEntity().getContent(), PartnersLinkResponse.class);
        System.out.println(partnersLinkResponse);

        return partnersLinkResponse.getData().get(0).getShortenUrl();
    }
}
