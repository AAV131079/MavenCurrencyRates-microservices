package com.currencyratesprovider.controller;

import org.springframework.web.bind.annotation.PathVariable;

public interface IProviderController {
    public String getProviderResponce(@PathVariable String provider);
}