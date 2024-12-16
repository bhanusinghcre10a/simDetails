package com.simactivation.handler;

import com.simactivation.repository.CustomerRepositoryExtended;
import com.simactivation.repository.CustomerRowMapper;
import net.bytebuddy.implementation.bytecode.StackSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Map;

public class GlobalHandler {


    public ModelAndView requestHandler(){
        return new ModelAndView(new GlobalView());
    }

    public static class GlobalView implements View{
        @Autowired
        CustomerRepositoryExtended customerRepositoryExtended;

        @Override
        public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
            try{
                InputStream is = request.getPart("file").getInputStream();
                response.getWriter().write(new String(is.readAllBytes()));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            response.addHeader("response","default");
            response.addCookie(new Cookie("testCookie","cookieValue"));
            response.setStatus(200);


        }
    }
}
