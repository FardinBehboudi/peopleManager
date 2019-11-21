package com.assecor.showcase.peoplemanager.service;

import com.assecor.showcase.peoplemanager.IntegrationBaseTests;
import com.assecor.showcase.peoplemanager.exception.PersonNotFoundException;
import com.assecor.showcase.peoplemanager.model.Color;
import com.assecor.showcase.peoplemanager.model.PersonEntity;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author F_Behboudi@hotmail.com
 */
public class PersonServiceTest extends IntegrationBaseTests {

    @Autowired
    private PersonService classUnderTest;

    @Test
    public void findAll__ReturnAllPeople() {

        //Arrange
        String expectedFirstName = "Hans";

        //Act
        List<PersonEntity> personEntities = classUnderTest.findAll();

        //Assert
        Assert.assertNotNull(personEntities);
        Assert.assertThat(personEntities.size(), Matchers.greaterThan(0));
        Assert.assertThat(personEntities.get(0).getName(), Matchers.equalTo(expectedFirstName));

    }

    @Test
    public void findPerson_ExistingPersonID_ReturnPerson() throws PersonNotFoundException {
        //Arrange
        int personId = 2;
        String expectedName = "Peter";
        String expectedLastName = "Petersen";
        String expectedZipcode = "18439";
        String expectedcity = "Stralsund";
        Color expectedColor = Color.GREEN;

        //Act
        PersonEntity person = classUnderTest.findPerson(personId);

        //Assert
        Assert.assertNotNull(person);
        Assert.assertThat(person.getId(), Matchers.equalTo(personId));
        Assert.assertThat(person.getName(), Matchers.equalTo(expectedName));
        Assert.assertThat(person.getLastname(), Matchers.equalTo(expectedLastName));
        Assert.assertThat(person.getZipcode(), Matchers.equalTo(expectedZipcode));
        Assert.assertThat(person.getCity(), Matchers.equalTo(expectedcity));
        Assert.assertThat(person.getColor(), Matchers.equalTo(expectedColor));


    }

    @Test(expected = PersonNotFoundException.class)
    public void findPerson_NonExistingPersonId_ThrowPersonNotFoundException() throws PersonNotFoundException {

        //Arrange
        int nonExistingPersonId = Integer.MAX_VALUE;

        //Act
        classUnderTest.findPerson(nonExistingPersonId);

    }

}
