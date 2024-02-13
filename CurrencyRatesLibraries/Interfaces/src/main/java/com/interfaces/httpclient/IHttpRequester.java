package com.interfaces.httpclient;

import java.io.IOException;

public interface IHttpRequester {
   public String getRequest(String serviceUrl) throws IOException;
   public void postRequest();
}