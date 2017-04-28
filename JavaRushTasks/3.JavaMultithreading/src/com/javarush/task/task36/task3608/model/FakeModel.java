package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NazarenkoDS on 28.04.2017.
 */
public class FakeModel implements Model {
   private ModelData modelData = new ModelData();

    @Override
    public void loadUsers() {
        List list = new ArrayList<>();
        list.add(new User("A", 1, 1));
        list.add(new User ("B", 2, 1));
        modelData.setUsers(list);
    }



    @Override
    public ModelData getModelData() {
        return this.modelData;
    }

    @Override
    public void loadDeletedUsers() {
        throw new UnsupportedOperationException();
    }
}
