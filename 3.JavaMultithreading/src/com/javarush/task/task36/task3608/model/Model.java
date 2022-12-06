package com.javarush.task.task36.task3608.model;

public interface Model {
    public ModelData getModelData();
    void loadUsers();
    public void changeUserData(String name, long id, int level);

    void loadDeletedUsers();

    void loadUserById(long userId);
    public void deleteUserById(long id);
}
