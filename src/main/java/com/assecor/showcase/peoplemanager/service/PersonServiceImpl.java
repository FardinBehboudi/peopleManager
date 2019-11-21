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

/**
 * @author F_Behboudi@hotmail.com
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    @Qualifier(value = "personFileRepository")
    private PersonRepository personRepository;

    /**
     *
     * @return list of all available person in repository
     */
    @Override
    public List<PersonEntity> findAll() {
        return personRepository.findAll();
    }

    /**
     * fetches a person based on given ID
     * @param id of the given person
     * @return a PersonEntity object
     * @throws PersonNotFoundException will be thrown if the queried person ID does not exist.
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public PersonEntity findPerson(int id) throws PersonNotFoundException {
        try {
            return personRepository.findPerson(id);
        } catch (IllegalArgumentException ex) {
            throw new PersonNotFoundException(ex.getMessage());

        }
    }

    /**
     * finds list of the people with the mentioned Color.
     * @param color to be checked amonge PersonEntity Objects, it shoudl match list of colors in Enum object.
     * @return List of the PersonEntity Objects , returns empty list if there is none.
     * @throws ColorNotFoundException will be thrown if the queried color is not amonge Enum elements
     */
    @Override
    public List<PersonEntity> findPersonByColor(String color) throws ColorNotFoundException {
        try {
            return personRepository.findPersonByColor(color);
        } catch (IllegalArgumentException ex) {
            throw new ColorNotFoundException(ex.getMessage());

        }
    }

    /**
     * adds the new PersonEntity object, which is posted via Rest Interface to file or database.
     * @param personEntity the PersonEntity Object that will be added to repository
     */
    @Override
    @Transactional
    public void add(PersonEntity personEntity) {
        personRepository.add(personEntity);
    }


}
