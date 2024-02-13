package com.dto.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlRequestDTO {

    private String url;

    public UrlRequestDTO(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("{%s}", getUrl());
    }

}