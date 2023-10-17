package com.httpclient.service;

import com.httpclient.httprequester.HttpRequesterImpl;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class HttpClientService {

    private final HttpRequesterImpl httpRequester;

    public HttpClientService(HttpRequesterImpl httpRequester) {
        this.httpRequester = httpRequester;
    }

    public String getServiceResponse(String serviceUrl) throws IOException {
        return httpRequester.getRequest(serviceUrl);
    }

}