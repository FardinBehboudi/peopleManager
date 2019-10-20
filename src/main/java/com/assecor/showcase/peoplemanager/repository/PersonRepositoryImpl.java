package com.assecor.showcase.peoplemanager.repository;


import com.assecor.showcase.peoplemanager.model.Color;
import com.assecor.showcase.peoplemanager.model.PersonEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonRepositoryImpl implements PersonRepository {

    private List<PersonEntity> personEntities;

    @Override
    public void readAll() throws FileNotFoundException, IOException {
        personEntities = new ArrayList<>();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("persondata.csv").getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line= null;
        int linenumber = 0;
        while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
            linenumber++;
            String[] fields = line.split(",");
            String lastname = fields[0].trim();
            String name = fields[1].trim();
            fields[2] = fields[2].trim();
            String zipcode = fields[2].substring(0,fields[2].indexOf(" "));
            String city = fields[2].substring(fields[2].indexOf(" ") + 1);
            Integer colorNumber = Integer.valueOf(fields[3].trim());

            PersonEntity personEntity = new PersonEntity(linenumber,lastname, name, zipcode,city, Color.fromColorNumber(colorNumber));
            personEntities.add(personEntity);
        }
        bufferedReader.close();

    }

    @Override
    public List<PersonEntity> findAll() {
        return personEntities;
    }
}
