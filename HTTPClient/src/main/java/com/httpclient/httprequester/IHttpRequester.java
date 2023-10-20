package com.httpclient.httprequester;

import java.io.IOException;

public interface IHttpRequester {
   public String getRequest(String serviceUrl) throws IOException;
   public void postRequest();
}