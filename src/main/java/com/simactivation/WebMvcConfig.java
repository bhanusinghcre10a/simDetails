package com.simactivation;

import com.simactivation.handler.HandlerMapping;
import com.simactivation.interceptors.InterceptorLogger;
import com.simactivation.repository.CustomerRepositoryExtended;
import com.simactivation.repository.CustomerRowMapper;
import com.simactivation.util.ConfigFactory;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.logging.Handler;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorLogger());
    }

//    @Bean
//    public RequestMappingHandlerMapping createRequestMappingHandlerMapping(){
//            HandlerMapping  handlerMapping=  new HandlerMapping();
//            handlerMapping.setOrder(2);
//            return handlerMapping;
//    }

    @Bean
    public CustomerRepositoryExtended customerRepositoryExtended(){
        CustomerRepositoryExtended  customerRepositoryExtended=  new CustomerRepositoryExtended();
        return customerRepositoryExtended;
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ConfigFactory configFactory(){
        return new ConfigFactory();
    }


}
