package com.assecor.showcase.peoplemanager.service;

import com.assecor.showcase.peoplemanager.exception.ColorNotFoundException;
import com.assecor.showcase.peoplemanager.exception.PersonNotFoundException;
import com.assecor.showcase.peoplemanager.model.PersonEntity;
import com.assecor.showcase.peoplemanager.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public PersonEntity findPerson(int id) throws PersonNotFoundException {
        try {
            return personRepository.findPerson(id);
        } catch (IllegalArgumentException ex) {
            throw new PersonNotFoundException(ex.getMessage());

        }
    }

    @Override
    public List<PersonEntity> findPersonByColor(String color) throws ColorNotFoundException {
        try {
            return personRepository.findPersonByColor(color);
        } catch (IllegalArgumentException ex) {
            throw new ColorNotFoundException(ex.getMessage());

        }
    }

    @Override
    @Transactional
    public void add(PersonEntity personEntity) {
        personRepository.add(personEntity);
    }


}
