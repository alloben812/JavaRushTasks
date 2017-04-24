package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.List;

/**
 * Created by NazarenkoDS on 21.04.2017.
 */
public class ModelData {
    private List<User> users;
    private boolean displayDeletedUserList;

    public void setDisplayDeletedUserList(boolean displayDeletedUserList) {
        this.displayDeletedUserList = displayDeletedUserList;
    }

    public boolean isDisplayDeletedUserList() {

        return displayDeletedUserList;
    }

     private User activeUser;

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public User getActiveUser() {

        return activeUser;
    }

    public List<User> getUsers(){
        return users;
    }
    public void setUsers(List<User> users){
        this.users=users;
    }
}
