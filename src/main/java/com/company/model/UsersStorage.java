package com.company.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class UsersStorage {
    private ArrayList<User> users;

    public UsersStorage(){
        readFromFile();
    }

    public void addUser(User user) {
        users.add(user);
        writeInFile();
    }

    public User findUser(String firstName, String lastName){
        User result = null;
        for (User user : users) {
            if(user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)){
                result = user;
                break;
            }
        }
        return result;
    }

    private void writeInFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("users.json"), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            users = mapper.readValue(new File("users.json"), new TypeReference<ArrayList<User>>() {});
        } catch (MismatchedInputException e) {
            users = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User addUser(MultipartFile file) {
        ObjectMapper mapper = new ObjectMapper();
        User newUser = null;
        File tmpFile = new File("tmp.json");
        try {
            tmpFile.createNewFile();
            file.transferTo(tmpFile.toPath());
            newUser = mapper.readValue(tmpFile, new TypeReference<User>() {});
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            tmpFile.delete();
        }
        if(isUserValid(newUser)){
            addUser(newUser);
        } else {
            newUser = null;
        }
        return newUser;
    }

    private boolean isUserValid(User newUser) {
        if (newUser==null)
            return false;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(newUser);
        return violations.isEmpty();
    }
}
