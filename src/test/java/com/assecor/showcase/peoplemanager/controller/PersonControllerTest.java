package com.assecor.showcase.peoplemanager.controller;

import com.assecor.showcase.peoplemanager.IntegrationBaseTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author F_Behboudi@hotmail.com
 */
@AutoConfigureMockMvc
public class PersonControllerTest extends IntegrationBaseTests {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPersons__ReturnAllPeople() throws Exception {
        //Assert
        String url = "/persons";
        //Act and Assert
        mockMvc.perform(get(url))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }

}




