package org.xupt.cat.proxy.api.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        //驼峰转下划线
        //objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static Map toMap(Object object) {
        try {
            return objectMapper.convertValue(object, Map.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return (T)objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T fromJson(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    public static <T> T fromJson(String json, TypeReference<T> type){
        try {
            return (T) objectMapper.readValue(json, type);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}

