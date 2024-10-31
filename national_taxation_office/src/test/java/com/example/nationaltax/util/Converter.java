package com.example.nationaltax.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String obj2json(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T json2obj(String json, TypeReference<T> valueType) throws JsonProcessingException {
        return objectMapper.readValue(json, valueType);
    }
}
