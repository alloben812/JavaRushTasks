package com.javarush.task.task36.task3608.model;

/**
 * Created by NazarenkoDS on 21.04.2017.
 */
public interface Model {
    ModelData getModelData();
    void loadUsers();
    public void loadDeletedUsers();
}
