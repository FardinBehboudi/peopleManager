package com.assecor.showcase.peoplemanager.controller;

import com.assecor.showcase.peoplemanager.model.PersonEntity;
import com.assecor.showcase.peoplemanager.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/persons/{id}")
    public PersonEntity getPerson(@PathVariable("id") int id){

        return personService.findPerson(id);
    }


    @GetMapping("/persons/color/{color}")
    public List<PersonEntity> getPersonByColor(@PathVariable("color") String color){

        return personService.findPersonByColor(color);
    }


    @PostMapping("/persons")
    public void addPerson(PersonEntity personEntity) {
         personService.add(personEntity);
    }






}
