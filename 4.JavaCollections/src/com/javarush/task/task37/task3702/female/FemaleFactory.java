package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.Human;

public class FemaleFactory {

    public Human getPerson(int age) {
        if (age > 0 && age < 13) {
            return new KidGirl();
        } else if (age > 12 && age < 20) {
            return new TeenGirl();
        } else {
            return new Woman();
        }
    }
}
