package com.javarush.task.task15.task1522;

public class Sun implements Planet {
    private static Sun instance = null;


    private Sun() {
    }

    public static Sun getInstance() {

        if (instance == null) {
            synchronized (Sun.class) {
                instance = new Sun();
            }
        }
        return instance;
    }
}