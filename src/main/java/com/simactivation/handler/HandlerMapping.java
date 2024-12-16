package com.simactivation.handler;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;

public class HandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected HandlerMethod getHandlerInternal(HttpServletRequest request) throws Exception {
        return GlobalMapping.requestHandler(request);
    }
}
