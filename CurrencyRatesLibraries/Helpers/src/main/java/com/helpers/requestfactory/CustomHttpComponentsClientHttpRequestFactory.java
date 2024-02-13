package com.helpers.requestfactory;

import org.apache.http.client.methods.HttpUriRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.net.URI;

public final class CustomHttpComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

    @Override
    protected @NotNull HttpUriRequest createHttpUriRequest(@NotNull HttpMethod httpMethod, URI uri) {

        if (HttpMethod.GET.equals(httpMethod)) {
            return new HttpEntityEnclosingGetRequestBase(uri);
        }
        return super.createHttpUriRequest(httpMethod, uri);
    }

}