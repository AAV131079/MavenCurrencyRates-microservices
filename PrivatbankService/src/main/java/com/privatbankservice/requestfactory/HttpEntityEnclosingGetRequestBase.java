package com.privatbankservice.requestfactory;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.springframework.http.HttpMethod;

import java.net.URI;

public final class HttpEntityEnclosingGetRequestBase extends HttpEntityEnclosingRequestBase {

    public HttpEntityEnclosingGetRequestBase(final URI uri) {
        super.setURI(uri);
    }

    @Override
    public String getMethod() {
        return HttpMethod.GET.name();
    }

}