package com.httpclient.service;

import com.helpers.string.StringHelper;
import com.httpclient.httprequester.HttpRequesterImpl;
import com.interfaces.httpclient.IHttpClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
@ComponentScan({"com.helpers"})
public class HttpClientService implements IHttpClientService {

    private final HttpRequesterImpl httpRequester;
    private final StringHelper stringHelper;

    @Autowired
    public HttpClientService(HttpRequesterImpl httpRequester, StringHelper stringHelper) {
        this.httpRequester = httpRequester;
        this.stringHelper = stringHelper;
    }

    @Override
    public String getServiceResponse(String serviceUrl) throws IOException {
        String response = null;
        int counter = 1;
        log.info("{HttpClientService::getServiceResponse}");
        log.info("ServiceUrl: {}", serviceUrl);
        while (Objects.isNull(response)) {
            log.info("Trying to get a response from the server: {}", counter++);
            response = httpRequester.getRequest(serviceUrl);
        }
        log.info("Response: {}", response);
        log.info("Response size: {}", response.length());
        return response;
    }

}