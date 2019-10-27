package com.assecor.showcase.peoplemanager.repository;

import com.assecor.showcase.peoplemanager.model.PersonEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface PersonRepository {

    void readAll() throws FileNotFoundException, IOException;
    List<PersonEntity> findAll();
    PersonEntity findPerson(int id);
    List<PersonEntity> findPersonByColor(String color);
    void add(PersonEntity personEntity) ;

}
