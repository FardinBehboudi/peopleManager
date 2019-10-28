package com.assecor.showcase.peoplemanager.repository;


import com.assecor.showcase.peoplemanager.model.Color;
import com.assecor.showcase.peoplemanager.model.PersonEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component(value = "personFileRepository")
@Primary
public class PersonRepositoryImpl implements PersonRepository {

    private List<PersonEntity> personEntities;
    private static final Logger logger = Logger.getLogger(Color.class.getName());


    @Override
    public void readAll() throws FileNotFoundException, IOException {
        personEntities = new ArrayList<>();

        //gets project directory
        String projectPath = System.getProperty("user.dir");
        int lastSlash = projectPath.lastIndexOf('\\');
        String filePath= projectPath.substring(0,lastSlash+1);
        //open files from project directory
        File file = new File(filePath + "persondata.csv");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("persondata.csv").getFile());
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line= null;
        int linenumber = 0;
        while ((line = bufferedReader.readLine()) != null && !line.trim().equals("")) {
            linenumber++;
            String[] fields = line.split(",");
            String lastname = fields[0].trim();
            String name = fields[1].trim();
            fields[2] = fields[2].trim();
            String zipcode = fields[2].substring(0,fields[2].indexOf(" "));
            String city = fields[2].substring(fields[2].indexOf(" ") + 1);
            Integer colorNumber = Integer.valueOf(fields[3].trim());

            PersonEntity personEntity = new PersonEntity(linenumber, name, lastname, zipcode,city, Color.fromColorNumber(colorNumber));
            personEntities.add(personEntity);
        }
        bufferedReader.close();

    }

    @Override
    public List<PersonEntity> findAll() {
        return personEntities;
    }

    @Override
    public PersonEntity findPerson(int id) {
        for(PersonEntity personEntity : personEntities ){
            if(personEntity.getId() == id){
                return personEntity;
            }
        }
        String errorMessage = String.format("person with id %d is not defined", id);
        logger.warning(errorMessage);
        throw new IllegalArgumentException(errorMessage);

    }

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
        } catch (IllegalArgumentException ex){
            String errorMessage = String.format("person with id %s is not defined", colorName);
            logger.warning(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }


    }

    @Override
    public void add(PersonEntity personEntity)  {
        int id = personEntities.size()+1;
        personEntity.setId(id);

        try {
            persistEntity(personEntity);

        } catch (IOException e) {
            e.printStackTrace();

        }
        personEntities.add(personEntity);

    }

    private void persistEntity(PersonEntity personEntity) throws FileNotFoundException, IOException{

        //gets project directory
        String projectPath = System.getProperty("user.dir");
        int lastSlash = projectPath.lastIndexOf('\\');
        String filePath= projectPath.substring(0,lastSlash+1);
        //writes to project directory
        File file = new File(filePath + "persondata.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));

//            ClassLoader classLoader = getClass().getClassLoader();
//            File file = new File(classLoader.getResource("persondata.csv").getPath());
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
        try{
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
        }catch(IOException ioe){
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
        } finally {
            bufferedWriter.close();

        }
    }

}
