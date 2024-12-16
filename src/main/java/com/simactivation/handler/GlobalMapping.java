package com.simactivation.handler;

import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;

public class GlobalMapping {

    public static HandlerMethod requestHandler(HttpServletRequest request) throws NoSuchMethodException {
        return new HandlerMethod(new GlobalHandler(),GlobalHandler.class.getMethod("requestHandler"));
    }


}
