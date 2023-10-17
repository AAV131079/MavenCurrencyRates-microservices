package com.httpclient.controller;

import com.httpclient.service.HttpClientService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(HttpClientController.class)
class HttpClientControllerTest {

    private final String inputJson = "{\"url\": \"https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5\"}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HttpClientService httpClientService;

    @Test
    void getServiceResponse() throws Exception {
        mockMvc.perform(
                get("/api/get/currencyrates")
                        .content(inputJson)
                        .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains("\"ccy\":\"EUR\",\"base_ccy\":\"UAH\"");
    }

}