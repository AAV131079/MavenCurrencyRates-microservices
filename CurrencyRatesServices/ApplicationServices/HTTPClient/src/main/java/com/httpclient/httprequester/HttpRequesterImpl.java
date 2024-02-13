package com.httpclient.httprequester;

import com.interfaces.httpclient.IHttpRequester;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Slf4j
@Component
public class HttpRequesterImpl implements IHttpRequester {

    private final StringBuffer response = new StringBuffer();

    @Override
    public String getRequest(String serviceUrl) throws IOException {
        URL url = new URL(serviceUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        try (BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            log.info("Sending 'GET' request to URL: {}", serviceUrl);
            String inputLine;
            response.delete(0, response.length());
            while ((inputLine = input.readLine()) != null) {
                response.append(inputLine);
            }
            log.info("Response code : {}", connection.getResponseCode());
            return response.toString();
        }
    }

    @Override
    public void postRequest() {
    }

}