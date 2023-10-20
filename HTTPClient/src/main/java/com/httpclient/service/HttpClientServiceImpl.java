package com.httpclient.service;

import com.httpclient.httprequester.HttpRequesterImpl;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class HttpClientServiceImpl implements IHttpClientService {

    private final HttpRequesterImpl httpRequester;

    public HttpClientServiceImpl(HttpRequesterImpl httpRequester) {
        this.httpRequester = httpRequester;
    }

    @Override
    public String getServiceResponse(String serviceUrl) throws IOException {
        return httpRequester.getRequest(serviceUrl);
    }

}