package com.scp.service;

import com.scp.Exception.AppException;
import com.scp.Exception.InternalStandardError;
import com.scp.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PersonService {

    @Value("${app.external.person-service.add-url}")
    private String addUrl;

    @Value("${app.external.person-service.search-url}")
    private String searchUrl;

    //@Autowired
    private AuthService authService;

    private RestTemplate restTemplate;

    public PersonService(RestTemplateBuilder restTemplateBuilder, AuthService authService) {
        this.restTemplate = restTemplateBuilder.build();
        this.authService = authService;
    }

    public String createPerson(Person person){
        String token = authService.getAuthToken();
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", token);
            HttpEntity<Person> httpEntity = new HttpEntity<>(person, httpHeaders);
            ResponseEntity<Person> response = restTemplate.exchange(addUrl, HttpMethod.POST, httpEntity, Person.class);
            log.info("add person successfully with {}",response.getStatusCode());
            return "person added successfully";

        }catch (HttpStatusCodeException ex){
            if (ex.getStatusCode().is4xxClientError()){
                throw new AppException(InternalStandardError.PERSON_CLIENT_ERROR);
            }
            throw new AppException(InternalStandardError.PERSON_SERVER_ERROR);
        }
    }

    public Person searchPerson(String personName, String mobileNumber){
        String token = authService.getAuthToken();
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", token);
            httpHeaders.add("personName", personName);
            httpHeaders.add("mobile", mobileNumber);
            HttpEntity<?> httpEntity = new HttpEntity<>( httpHeaders);
            ResponseEntity<Person> response = restTemplate.exchange(searchUrl, HttpMethod.GET, httpEntity, Person.class);
            log.info("add person successfully with {}",response.getStatusCode());
            return response.getBody();

        }catch (HttpStatusCodeException ex){
            if (ex.getStatusCode().is4xxClientError()){
                throw new AppException(InternalStandardError.PERSON_CLIENT_ERROR);
            }
            throw new AppException(InternalStandardError.PERSON_SERVER_ERROR);
        }
    }
}
