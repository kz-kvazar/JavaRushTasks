package com.javarush.task.task36.task3608.model;

public interface Model {
    public ModelData getModelData();
    void loadUsers();

    void loadDeletedUsers();
}
