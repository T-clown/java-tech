package util;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson2.JSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.commons.lang3.StringUtils;

public class ParserDataUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T parseData(String jsonData, Class<T> clazz) {
        if (StringUtils.isBlank(jsonData)) {
            return null;
        }
        T result = null;
        try {
            result = OBJECT_MAPPER.readValue(jsonData, clazz);
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
            CollectionType type = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
            result = OBJECT_MAPPER.readValue(jsonData, type);
        } catch (IOException e) {
        }
        return result;
    }
    

}

