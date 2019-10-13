package com.assecor.showcase.peoplemanager.controller;

import com.assecor.showcase.peoplemanager.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping("persons")
    public Person getPersons(){
        return new Person("Fardin", "Behboudi");
    }
}
