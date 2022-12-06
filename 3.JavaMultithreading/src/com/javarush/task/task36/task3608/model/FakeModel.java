package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.ArrayList;
import java.util.List;

public class FakeModel implements Model {
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        List<User> us = new ArrayList<>();
        us.add(new User("A", 1, 1));
        us.add(new User("B", 2, 1));
        modelData.setUsers(us);
    }

    @Override
    public void changeUserData(String name, long id, int level) {
        throw  new UnsupportedOperationException();
    }

    public void loadDeletedUsers() {

    }

    @Override
    public void loadUserById(long userId) {
        throw  new UnsupportedOperationException();
    }

    @Override
    public void deleteUserById(long id) {
        throw  new UnsupportedOperationException();
    }
}
