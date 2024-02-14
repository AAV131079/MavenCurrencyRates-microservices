package com.interfaces.httpclient;

import java.io.IOException;

public interface IHttpClientService {
    public String getServiceResponse(String serviceUrl) throws IOException, InterruptedException;
}
