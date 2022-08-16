package com.example.hotdealnoti.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectMappingUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static HashMap<String, Object> objectToHashMap(Object object) {
        return objectMapper.convertValue(object, HashMap.class);
    }

    public static <T> T hashMapToObject(Map<String, Object> map, Class<T> typeToken) {
        Field[] declaredFields = typeToken.getDeclaredFields();
        HashMap<String, Object> targetMap = new HashMap<>();
        for(Field declaredField : declaredFields) {
            targetMap.put(declaredField.getName(), map.get(declaredField.getName()));
        }
        return objectMapper.convertValue(targetMap,typeToken);
    }

}
