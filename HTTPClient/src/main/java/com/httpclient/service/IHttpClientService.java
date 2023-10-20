package com.httpclient.service;

import java.io.IOException;

public interface IHttpClientService {
    public String getServiceResponse(String serviceUrl) throws IOException;
}
