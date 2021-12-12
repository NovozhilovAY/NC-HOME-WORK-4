package com.company.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
}
