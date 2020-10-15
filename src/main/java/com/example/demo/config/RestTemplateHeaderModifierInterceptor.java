package com.example.demo.config;

import com.example.demo.config.TenantContext;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {
    public RestTemplateHeaderModifierInterceptor(){

    }
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException{
        String projectId = TenantContext.getCurrentTenant();
        request.getHeaders().add("X-TenantID",projectId);
        ClientHttpResponse response = clientHttpRequestExecution.execute(request,body);
        response.getHeaders().add("X=TenantID",projectId);
        return response;
    }
}
