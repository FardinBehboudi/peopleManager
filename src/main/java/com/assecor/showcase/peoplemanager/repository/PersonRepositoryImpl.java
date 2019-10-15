package com.assecor.showcase.peoplemanager.repository;


import com.assecor.showcase.peoplemanager.model.PersonEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class PersonRepositoryImpl implements PersonRepository {

    private List<PersonEntity> personEntities;

    @Override
    public void readAll() {
        //read from CSV
        //put in list of persons : cache
    }

    @Override
    public List<PersonEntity> findAll() {
        return personEntities;
    }
}
