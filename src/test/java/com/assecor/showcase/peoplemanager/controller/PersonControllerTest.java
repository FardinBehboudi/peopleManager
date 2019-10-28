package com.assecor.showcase.peoplemanager.controller;

import com.assecor.showcase.peoplemanager.IntegrationBaseTests;
import com.assecor.showcase.peoplemanager.model.PersonEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@AutoConfigureMockMvc
public class PersonControllerTest extends IntegrationBaseTests {



    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPersons__ReturnAllPeople() throws Exception {
       // this.mockMvc.perform(get("/persons")).andExpect(status().isOk());
        
    }

}
