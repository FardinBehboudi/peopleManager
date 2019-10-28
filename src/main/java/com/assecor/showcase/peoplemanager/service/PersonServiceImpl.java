package com.assecor.showcase.peoplemanager.service;

import com.assecor.showcase.peoplemanager.exception.ColorNotFoundException;
import com.assecor.showcase.peoplemanager.exception.PersonNotFoundException;
import com.assecor.showcase.peoplemanager.model.Color;
import com.assecor.showcase.peoplemanager.model.PersonEntity;
import com.assecor.showcase.peoplemanager.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    @Qualifier(value = "personFileRepository")
    private PersonRepository personRepository;

    @Override
    public List<PersonEntity> findAll() {
        return personRepository.findAll();
    }

    @Override
    public PersonEntity findPerson(int id) throws PersonNotFoundException {
        try {
            return personRepository.findPerson(id);
        }
        catch (IllegalArgumentException ex){
            throw new PersonNotFoundException(ex.getCause());

        }
    }

    @Override
    public List<PersonEntity> findPersonByColor(String color) throws ColorNotFoundException {
        try {
            return personRepository.findPersonByColor(color);
        }
        catch (IllegalArgumentException ex){
            throw new ColorNotFoundException(ex.getCause());

        }
    }

    @Override
    public void add(PersonEntity personEntity)  {  personRepository.add(personEntity);}


}
