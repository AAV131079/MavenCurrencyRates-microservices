package com.interfaces.provider;

import org.springframework.web.bind.annotation.PathVariable;

public interface IProviderController {
    public String getProviderResponse(@PathVariable String provider) throws InterruptedException;
}