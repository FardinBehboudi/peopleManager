package com.assecor.showcase.peoplemanager.repository;


import com.assecor.showcase.peoplemanager.model.Color;
import com.assecor.showcase.peoplemanager.model.PersonEntity;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author F_Behboudi@hotmail.com
 * reads the list of persons from a given file and adds them to PersonEntity Object, and exposes them to Rest Interface.
 * writes new peoples records to the same file.
 * "persondata.csv" is list of people. this file will be cached while starting the application.
 */
@Component(value = "personFileRepository")
public class PersonRepositoryImpl implements PersonRepository {

    private List<PersonEntity> personEntities;
    private static final Logger logger = Logger.getLogger(PersonRepositoryImpl.class.getName());


    /**
     * Repository to read and write from/to File.
     * while starting the application will look for "persondata.csv" on the parent folder of project.
     * the file is placed there because we need to read and write at the same time and it was not possible to do it resource path.
     * if the project exist,data from file will be fetched and if there is no such a file a new file will be created.
     * there is no change in file, all the adjustments are handled in code.
     *
     * @throws IOException
     */
    @Override
    public void readAll() throws IOException {
        personEntities = new ArrayList<>();

        //gets project directory
        String projectPath = System.getProperty("user.dir");
        int lastSlash = projectPath.lastIndexOf('\\');
        String filePath = projectPath.substring(0, lastSlash + 1);
        String fileName = "persondata.csv";
        //open files from project directory
        File file = new File(filePath + fileName);
        file.createNewFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));


        String line = null;
        int linenumber = 0;
        while ((line = bufferedReader.readLine()) != null && !line.trim().equals("")) {
            linenumber++;
            String[] fields = line.split(",");
            String lastname = fields[0].trim();
            String name = fields[1].trim();
            fields[2] = fields[2].trim();
            String zipcode = fields[2].substring(0, fields[2].indexOf(" "));
            String city = fields[2].substring(fields[2].indexOf(" ") + 1);
            Integer colorNumber = Integer.valueOf(fields[3].trim());

            PersonEntity personEntity = new PersonEntity(linenumber, name, lastname, zipcode, city, Color.fromColorNumber(colorNumber));
            personEntities.add(personEntity);
        }
        bufferedReader.close();

    }

    /**
     * @return list of peoples
     */
    @Override
    public List<PersonEntity> findAll() {
        return personEntities;
    }

    /**
     * searches for person by its ID
     *
     * @param id
     * @return a PersonEntity Object
     */
    @Override
    public PersonEntity findPerson(int id) {
        for (PersonEntity personEntity : personEntities) {
            if (personEntity.getId() == id) {
                return personEntity;
            }
        }
        String errorMessage = String.format("person with id %d is not defined", id);
        logger.warning(errorMessage);
        throw new IllegalArgumentException(errorMessage);

    }

    /**
     * @param colorName
     * @return all people with the mentioned Color
     */
    @Override
    public List<PersonEntity> findPersonByColor(String colorName) {
        List<PersonEntity> personEntityList = new ArrayList<>();
        Color color = Color.valueOf(colorName.toUpperCase());
        try {
            for (PersonEntity personEntity : personEntities) {
                if (personEntity.getColor() == color) {
                    personEntityList.add(personEntity);
                }
            }
            return personEntityList;
        } catch (IllegalArgumentException ex) {
            String errorMessage = String.format("person with id %s is not defined", colorName);
            logger.warning(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }


    }

    /**
     * adds new person to file.
     * person ID is defined based on the line number of each record
     *
     * @param personEntity
     */
    @Override
    public void add(PersonEntity personEntity) {
        int id = personEntities.size() + 1;
        personEntity.setId(id);

        try {
            persistEntity(personEntity);

        } catch (IOException e) {
            e.printStackTrace();

        }
        personEntities.add(personEntity);

    }

    /**
     * persists given Person Entity to file , fue to keeping consistency of file Zipcode and City are merged again.
     *
     * @param personEntity
     * @throws IOException
     */
    private void persistEntity(PersonEntity personEntity) throws IOException {

        //gets project directory
        String projectPath = System.getProperty("user.dir");
        int lastSlash = projectPath.lastIndexOf('\\');
        String filePath = projectPath.substring(0, lastSlash + 1);
        //writes to project directory
        File file = new File(filePath + "persondata.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));

        try {
            bufferedWriter.append("\n");
            bufferedWriter.append(personEntity.getLastname());
            bufferedWriter.append(", ");
            bufferedWriter.append(personEntity.getName());
            bufferedWriter.append(", ");
            bufferedWriter.append(personEntity.getZipcode()).append(" ").append(personEntity.getCity());
            bufferedWriter.append(", ");
            bufferedWriter.append(String.valueOf(Color.fromColorName(personEntity.getColor().name())));
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException ioe) {
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
        } finally {
            bufferedWriter.close();

        }
    }

}
