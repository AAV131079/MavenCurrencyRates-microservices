package com.helpers.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ObjectMapperHelper {

    private final ObjectMapper objectMapper;

    @Autowired
    public ObjectMapperHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Object fillDTO(String response, @NotNull Class<?> dtoClass) throws JsonProcessingException {
        log.info("response: " + response + dtoClass);
        return objectMapper.readValue(response, dtoClass);
    }

}
