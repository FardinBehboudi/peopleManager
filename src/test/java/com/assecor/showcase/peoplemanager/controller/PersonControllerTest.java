package com.assecor.showcase.peoplemanager.controller;

import com.assecor.showcase.peoplemanager.IntegrationBaseTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;



@AutoConfigureMockMvc
public class PersonControllerTest extends IntegrationBaseTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetPersonSuccess() {
        
    }

}
