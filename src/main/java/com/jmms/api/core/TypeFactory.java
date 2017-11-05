package com.jmms.api.core;

import com.google.gson.reflect.TypeToken;
import com.jmms.api.domain.common.ResponseInformation;
import com.jmms.api.domain.health.Status;
import com.jmms.api.domain.jwt.LoginInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class TypeFactory {

    private final static Map<Class, Type> typeMap;

    static {
        typeMap = new HashMap<>();
        typeMap.put(Status.class, new TypeToken<ResponseInformation<Status>>() {}.getType());
        typeMap.put(LoginInfo.class, new TypeToken<ResponseInformation<LoginInfo>>() {}.getType());
    }

    public static Type getType(Class classType) {
        return typeMap.get(classType);
    }

}
