package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }
public static SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    public static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    public static void main(String[] args) throws ParseException {
        switch (args[0]) {
            case "-c": {
                Date date = simpleDateFormat1.parse(args[3]);
                if (args[2].equals("м")) {
                    allPeople.add(Person.createMale(args[1], date));
                    System.out.println(allPeople.size() - 1);
                } else {
                    allPeople.add(Person.createFemale(args[1], date));
                    System.out.println(allPeople.size() - 1);
                }
                break;
            }
            case "-r": {
                int id = Integer.parseInt(args[1]);
                String name = allPeople.get(id).getName();
                System.out.println(name + " " + (allPeople.get(id).getSex() == Sex.MALE ? "м" : "ж") + " " + simpleDateFormat2.format(allPeople.get(id).getBirthDate()));
                break;
            }
            case "-u": {
                int id = Integer.parseInt(args[1]);
                allPeople.get(id).setName(args[2]);
                if (args[3].equals("м")) {
                    allPeople.get(id).setSex(Sex.MALE);
                } else {
                    allPeople.get(id).setSex(Sex.FEMALE);
                }
                allPeople.get(id).setBirthDate(simpleDateFormat1.parse(args[4]));
                break;
            }
            case "-d":
                int id = Integer.parseInt(args[1]);
                allPeople.get(id).setBirthDate(null);
                allPeople.get(id).setName(null);
                allPeople.get(id).setSex(null);
                break;
        }
        //напишите тут ваш код
    }
}
