package com.assecor.showcase.peoplemanager.hooks;

import com.assecor.showcase.peoplemanager.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author F_Behboudi@hotmail.com
 * this class implements Spring commandLineRunner to be called at application start up.
 */
@Component
public class StartupHook implements CommandLineRunner {

    @Autowired
    @Qualifier(value = "personFileRepository")
    private PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {

        personRepository.readAll();

    }
}
