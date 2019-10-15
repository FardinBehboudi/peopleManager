package com.assecor.showcase.peoplemanager.repository;

import com.assecor.showcase.peoplemanager.model.PersonEntity;

import java.util.List;

public interface PersonRepository {

    void readAll();

    List<PersonEntity> findAll();

}
