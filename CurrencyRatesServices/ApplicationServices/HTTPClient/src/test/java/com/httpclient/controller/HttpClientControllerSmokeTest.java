package com.httpclient.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest
public class HttpClientControllerSmokeTest {

    @Autowired
    private HttpClientController httpClientController;

    @Test
    void contextLoads() {
        assertThat(httpClientController).isNotNull();
    }

}