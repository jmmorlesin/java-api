package com.jmms.api.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import com.jmms.api.core.TypeFactory;

import java.lang.reflect.Type;

@Service
public class GsonService {

    private final Gson gson;

    public GsonService() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    public <T> T unparse(String json, Class classType) {
        Type type = TypeFactory.getType(classType);
        return gson.fromJson(json, type);
    }

    public Boolean isJSONValid(String jsonInString) {
        try {
            gson.fromJson(jsonInString, Object.class);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public <T> String parse(T object) {
        return gson.toJson(object);
    }

}
