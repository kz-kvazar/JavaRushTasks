package com.javarush.task.task14.task1411;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
User, Loser, Coder and Proger
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Person> people = new ArrayList<>();
        String s = reader.readLine();
        while (s.equals("user") || s.equals("loser") || s.equals("coder") || s.equals("proger")) {
            switch (s) {
                case "user":
                    Person user = new Person.User();
                    people.add(user);
                    break;
                case "loser":
                    Person loser = new Person.Loser();
                    people.add(loser);
                    break;
                case "coder":
                    Person coder = new Person.Coder();
                    people.add(coder);
                    break;
                case "proger":
                    Person proger = new Person.Proger();
                    people.add(proger);
                    break;
            }
            s = reader.readLine();
        }
        for (Person p: people) {
            doWork(p);
        }
    }
    public static void doWork(Person person) {
        // пункт 3
        if (person instanceof Person.User){
            ((Person.User) person).live();
        } else if (person instanceof Person.Loser) {
            ((Person.Loser) person).doNothing();
        } else if (person instanceof Person.Coder) {
            ((Person.Coder) person).writeCode();
        } else if (person instanceof Person.Proger) {
            ((Person.Proger) person).enjoy();
        }

    }
}
