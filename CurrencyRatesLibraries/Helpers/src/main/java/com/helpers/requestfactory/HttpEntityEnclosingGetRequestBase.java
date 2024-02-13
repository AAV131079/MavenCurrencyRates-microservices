package com.helpers.requestfactory;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpMethod;

import java.net.URI;

public final class HttpEntityEnclosingGetRequestBase extends HttpEntityEnclosingRequestBase {

    public HttpEntityEnclosingGetRequestBase(final URI uri) {
        super.setURI(uri);
    }

    @Contract(pure = true)
    @Override
    public @NotNull String getMethod() {
        return HttpMethod.GET.name();
    }

}