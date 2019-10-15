package com.assecor.showcase.peoplemanager.controller;

import com.assecor.showcase.peoplemanager.model.Color;
import com.assecor.showcase.peoplemanager.model.PersonEntity;
import com.assecor.showcase.peoplemanager.service.PersonService;
import com.assecor.showcase.peoplemanager.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public List<PersonEntity> getPersons(){
        return personService.findAll();
    }




}
