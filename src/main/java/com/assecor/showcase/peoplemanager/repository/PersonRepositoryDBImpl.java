package com.assecor.showcase.peoplemanager.repository;

import com.assecor.showcase.peoplemanager.model.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@Repository(value = "personDBRepository")
public class PersonRepositoryDBImpl implements PersonRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void readAll() throws FileNotFoundException, IOException {


    }

    @Override
    public List<PersonEntity> findAll() {
        //jpql
        TypedQuery<PersonEntity> query = entityManager.createQuery("SELECT c FROM PersonEntity c", PersonEntity.class);
        List<PersonEntity> results = query.getResultList();
        return results;
    }

    @Override
    public PersonEntity findPerson(int id) {
        return null;
    }

    @Override
    public List<PersonEntity> findPersonByColor(String color) {
        return null;
    }

    @Override
    public void add(PersonEntity personEntity) {

    }
}
