package com.scp.service;

import com.scp.Exception.AppException;
import com.scp.Exception.InternalStandardError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    @Value("${app.external.auth-service.url}")
    private String url;

    @Value("${app.external.auth-service.x-auth-role}")
    private String role;

    @Value("${app.external.auth-service.x-auth-user}")
    private String user;

    private RestTemplate restTemplate;

    public AuthService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getAuthToken(){
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("x-auth-role", role);
            httpHeaders.add("x-auth-user", user);
            HttpEntity httpEntity = new HttpEntity(httpHeaders);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            return response.getBody();
        }catch (HttpStatusCodeException ex){
            if (ex.getStatusCode().is4xxClientError()){
                //for customise exception
                // ex.getStatusCode().equals(HttpStatus.NOT_FOUND)
                throw new AppException(InternalStandardError.AUTH_CLIENT_ERROR);
            }
            throw new AppException(InternalStandardError.AUTH_SERVER_ERROR);
        }

    }
}
