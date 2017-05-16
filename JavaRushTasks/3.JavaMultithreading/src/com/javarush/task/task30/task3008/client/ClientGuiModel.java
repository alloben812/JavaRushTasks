package com.javarush.task.task30.task3008.client;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NazarenkoDS on 15.05.2017.
 */
public class ClientGuiModel {
    private final Set<String> allUserNames = new HashSet<>();
    private String newMessage;


    public ClientGuiModel() {
        allUserNames.add("Вася");
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    public Set<String> getAllUserNames() {
        return allUserNames;
    }

    public String getNewMessage() {
        return newMessage;
    }

    public void addUser(String newUserName){
        allUserNames.add(newUserName);
    }
    public void deleteUser(String userName){
         allUserNames.remove(userName);
    }
}
