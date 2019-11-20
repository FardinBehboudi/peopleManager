package com.assecor.showcase.peoplemanager.controller;

import com.assecor.showcase.peoplemanager.exception.ColorNotFoundException;
import com.assecor.showcase.peoplemanager.exception.PersonNotFoundException;
import com.assecor.showcase.peoplemanager.model.PersonEntity;
import com.assecor.showcase.peoplemanager.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public List<PersonEntity> getPersons() {

        return personService.findAll();
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Object> getPerson(@PathVariable("id") int id) throws PersonNotFoundException {
        try {
            return new ResponseEntity<>(personService.findPerson(id), HttpStatus.OK);
        } catch (PersonNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/persons/color/{color}")
    public ResponseEntity<Object> getPersonByColor(@PathVariable("color") String color) throws ColorNotFoundException {
        try {
            return new ResponseEntity<>(personService.findPersonByColor(color), HttpStatus.OK);
        } catch (ColorNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping("/persons")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Void> addPerson(PersonEntity personEntity) {
        personService.add(personEntity);
        return ResponseEntity.noContent().build();

    }


}
