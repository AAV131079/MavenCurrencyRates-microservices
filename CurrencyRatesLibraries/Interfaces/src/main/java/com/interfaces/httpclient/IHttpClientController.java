package com.interfaces.httpclient;

import com.dto.Request.UrlRequestDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

public interface IHttpClientController {
    public @ResponseBody String getServiceResponse(@RequestBody UrlRequestDTO request) throws IOException, InterruptedException;
}
