package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;

/* 
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable{

    private String string;

    @Override
    public String get(int index) {
        return string;
    }

    @Override
    public int size() {
        return string.length();
    }
}
