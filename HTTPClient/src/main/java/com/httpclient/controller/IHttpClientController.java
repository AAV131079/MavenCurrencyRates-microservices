package com.httpclient.controller;

import com.httpclient.dto.request.RequestDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

public interface IHttpClientController {
    public @ResponseBody String getServiceResponse(@RequestBody RequestDTO request) throws IOException;
}
