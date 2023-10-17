package com.httpclient.httprequester;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IHttpRequester {
   public String getRequest(String serviceUrl) throws IOException;
   public void postRequest();
}