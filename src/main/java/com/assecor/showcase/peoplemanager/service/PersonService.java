package com.assecor.showcase.peoplemanager.service;

import com.assecor.showcase.peoplemanager.model.PersonEntity;

import java.util.List;

public interface PersonService {

    List<PersonEntity> findAll();
}
