package com.assecor.showcase.peoplemanager.service;

import com.assecor.showcase.peoplemanager.exception.ColorNotFoundException;
import com.assecor.showcase.peoplemanager.exception.PersonNotFoundException;
import com.assecor.showcase.peoplemanager.model.PersonEntity;

import java.util.List;

public interface PersonService {

    List<PersonEntity> findAll();

    PersonEntity findPerson(int id) throws PersonNotFoundException;

    List<PersonEntity> findPersonByColor(String color) throws ColorNotFoundException;

    void add(PersonEntity personEntity);
}
