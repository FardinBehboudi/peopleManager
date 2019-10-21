package com.assecor.showcase.peoplemanager.service;

import com.assecor.showcase.peoplemanager.model.PersonEntity;
import com.assecor.showcase.peoplemanager.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonEntity> findAll() {
        return personRepository.findAll();
    }

    @Override
    public PersonEntity findPerson(int id) {
        return personRepository.findPerson(id);
    }

    @Override
    public List<PersonEntity> findPersonByColor(String color) {
        return personRepository.findPersonByColor(color);
    }

    @Override
    public void add(PersonEntity personEntity)  {  personRepository.add(personEntity);}


}
