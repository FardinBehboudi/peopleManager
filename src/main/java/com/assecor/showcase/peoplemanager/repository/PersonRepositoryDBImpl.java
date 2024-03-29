package com.assecor.showcase.peoplemanager.repository;

import com.assecor.showcase.peoplemanager.model.Color;
import com.assecor.showcase.peoplemanager.model.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author F_Behboudi@hotmail.com
 * repository to read and write from/to database.
 */
@Component(value = "personDBRepository")
public class PersonRepositoryDBImpl implements PersonRepository {

    private static final Logger logger = Logger.getLogger(PersonRepositoryDBImpl.class.getName());

    @Autowired
    private EntityManager entityManager;

    /**
     * can be implemented, IF we need to populate the database table from a file or another source for the first time.
     *
     * @throws IOException
     */
    @Override
    public void readAll() throws IOException {
    }

    /**
     * @return list of people
     */
    @Override
    public List<PersonEntity> findAll() {
        TypedQuery<PersonEntity> query = entityManager.createQuery("SELECT c FROM PersonEntity c", PersonEntity.class);
        List<PersonEntity> results = query.getResultList();
        return results;
    }

    /**
     * find person with given ID in database and in people table
     *
     * @param id of the person
     * @return an PersonEntity object
     */
    @Override
    public PersonEntity findPerson(int id) {
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

    /**
     * searches amonge records in database to find people with the mentioned favorite color
     *
     * @param colorName should be amonge the Enum elemnts
     * @return list of people and empty list ,if there is none
     */
    @Override
    public List<PersonEntity> findPersonByColor(String colorName) {
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

    /**
     * adds posted users to database
     *
     * @param personEntity
     */
    @Override
    public void add(PersonEntity personEntity) {
        entityManager.persist(personEntity);

    }


}
