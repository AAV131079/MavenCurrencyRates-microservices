package com.httpclient.controller;

import com.httpclient.service.HttpClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HttpClientController.class)
class HttpClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HttpClientService httpClientService;

    @Test
    void getServiceResponse() throws Exception {

        mockMvc.perform
                (
                    get("/api/get/currency-rates")
                            .content("{\"url\": \"https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5\"}")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains("[{\"ccy\":\"EUR\",\"base_ccy\":\"UAH\",\"buy\":\"");

    }

}