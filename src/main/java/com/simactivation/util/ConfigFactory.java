package com.simactivation.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ConfigFactory {

    public Config get(String config) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class mapper = null;
        try {
            mapper = Class.forName("com.simactivation.util." + config + "Mapper");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("not find mapper with name :" + config);
        }
        Mapper mapperObject = (Mapper)mapper.getConstructor().newInstance();
        Config configObject =  (Config)mapper.getDeclaredMethod("getConfig").invoke(mapperObject);
        return configObject;
    }
}
