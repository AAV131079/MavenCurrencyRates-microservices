package com.httpclient.service;

import com.httpclient.httprequester.HttpRequesterImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Slf4j
@Service
public class HttpClientService implements IHttpClientService {

    private final HttpRequesterImpl httpRequester;

    public HttpClientService(HttpRequesterImpl httpRequester) {
        this.httpRequester = httpRequester;
    }

    @Override
    public String getServiceResponse(String serviceUrl) throws IOException {
        log.info("{HttpClientService}");
        log.info(String.format("ServiceUrl: %s", serviceUrl));
        String response = httpRequester.getRequest(serviceUrl);
        log.info(String.format("Response: %s...", response.substring(0, 100)));
        log.info(String.format("Response size: %s", response.length()));
        return response;
    }

}