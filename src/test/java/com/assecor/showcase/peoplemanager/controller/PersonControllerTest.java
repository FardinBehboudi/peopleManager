package com.assecor.showcase.peoplemanager.controller;

import com.assecor.showcase.peoplemanager.IntegrationBaseTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
public class PersonControllerTest extends IntegrationBaseTests {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPersons__ReturnAllPeople() throws Exception {
        // this.mockMvc.perform(get("/persons")).andExpect(status().isOk());

    }

}
