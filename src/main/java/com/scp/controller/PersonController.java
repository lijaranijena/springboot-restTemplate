package com.scp.controller;

import com.scp.model.Person;
import com.scp.service.AuthService;
import com.scp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String createPerson(@RequestBody Person person){
        return personService.createPerson(person);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Person searchPerson(@RequestParam String personName, @RequestParam String mobileNumber){
        return personService.searchPerson(personName, mobileNumber);
    }
}
