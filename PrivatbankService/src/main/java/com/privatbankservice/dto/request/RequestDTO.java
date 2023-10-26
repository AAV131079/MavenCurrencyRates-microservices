package com.privatbankservice.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDTO {

    private String url;

    public RequestDTO(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("{%s}", getUrl());
    }

}