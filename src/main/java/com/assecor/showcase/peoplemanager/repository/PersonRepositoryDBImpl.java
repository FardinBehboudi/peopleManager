package com.assecor.showcase.peoplemanager.repository;

import com.assecor.showcase.peoplemanager.model.Color;
import com.assecor.showcase.peoplemanager.model.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;


@Component(value = "personDBRepository")
public class PersonRepositoryDBImpl implements PersonRepository {

    private static final Logger logger = Logger.getLogger(PersonRepositoryDBImpl.class.getName());

    @Autowired
    private EntityManager entityManager;

    @Override
    public void readAll() throws FileNotFoundException, IOException {
        //TODO

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
        //jpql
        try {
            TypedQuery<PersonEntity> query = entityManager.createQuery("SELECT c FROM PersonEntity c where c.id = ?1", PersonEntity.class);
            query.setParameter(1, id);
            PersonEntity result = query.getSingleResult();
            return result;
        } catch (NoResultException ex) {
            String errorMessage = String.format("person with id %s does not defined", id);
            logger.warning(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    @Override
    public List<PersonEntity> findPersonByColor(String colorName) {
        //jpql
        try {
            Color color = Color.valueOf(colorName.toUpperCase());
            TypedQuery<PersonEntity> query = entityManager.createQuery("SELECT c FROM PersonEntity c where c.color = ?1", PersonEntity.class);
            query.setParameter(1, color);
            List<PersonEntity> results = query.getResultList();
            return results;
        } catch (IllegalArgumentException ex) {
            String errorMessage = String.format("No such a color, named:  %s  exit", colorName);
            logger.warning(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    @Override
    public void add(PersonEntity personEntity) {
        entityManager.persist(personEntity);

    }


}
