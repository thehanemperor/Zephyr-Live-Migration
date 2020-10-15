package com.example.demo.config;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
* To determine which tenant is making requests.
* We will use Spring Interceptor to intercept the HTTP request and
* get the tenant information from the HTTP header
* */
@Component
public class RequestInterceptor extends  HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception{
        System.out.println("intercepting the Request at preHandle");
        System.out.println("___________________________________");
        String requestURI = request.getRequestURI();
        String tenantID = request.getHeader("X-TenantID");
        System.out.println("RequestURI::" + requestURI +" || Search for X-TenantID  :: " + tenantID);
        System.out.println("____________________________________________");
        if (tenantID == null){
            response.getWriter().write("X-TenantID can't be find");
            response.setStatus(400);
            return  false;
        }
        TenantContext.setCurrentTenant(tenantID);
        return  true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView
            ) throws Exception{
        TenantContext.clear();
    }
}
