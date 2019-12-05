package utils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson.JSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.commons.lang3.StringUtils;

public class ParserDataUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> List<T> convert(Object data, Class<T> clazz) {
        if (data instanceof List) {
            String jsonData = null;
            try {
                jsonData = mapper.writeValueAsString(data);
            } catch (JsonProcessingException e) {
            }
            // return parseDataArray(jsonData, clazz);
            return JSON.parseArray(jsonData, clazz);
        }
        return null;
    }

    public static <T> T parseData(String jsonData, Class<T> clazz) {
        if (StringUtils.isBlank(jsonData)) {
            return null;
        }
        T result = null;
        try {
            result = mapper.readValue(jsonData, clazz);
        } catch (IOException e) {
        }
        return result;
    }

    public static <T> List<T> parseDataArray(String jsonData, Class<T> clazz) {
        if (StringUtils.isBlank(jsonData)) {
            return null;
        }
        List<T> result = null;
        try {
            CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            result = mapper.readValue(jsonData, type);
        } catch (IOException e) {
        }
        return result;
    }

    public static <T> T parseObject(String data, Class<T> clazz) {
        return StringUtils.isBlank(data) ? null : JSON.parseObject(data, clazz);
    }

    public static <T> List<T> parseArray(String data, Class<T> clazz) {
        return StringUtils.isBlank(data) ?
            Collections.emptyList() : JSON.parseArray(data, clazz);
    }
    

}

