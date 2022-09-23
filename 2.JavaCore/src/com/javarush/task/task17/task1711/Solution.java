package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        switch (args[0]) {
            case "-c":
                synchronized (allPeople) {
                    for (int i = 1; args.length > i; i += 3) {
                        String name = args[i];
                        Date date = simpleDateFormat1.parse(args[i + 2]);
                        allPeople.add(args[i + 1].equals("м") ? Person.createMale(name, date) : Person.createFemale(name, date));
                        System.out.println(allPeople.size() - 1);
                    }
                }
                break;
            case "-u":
                synchronized (allPeople) {
                    for (int i = 1; args.length > i; i += 4) {
                        int id = Integer.parseInt(args[i]);
                        String name = args[i + 1];
                        Date date = simpleDateFormat1.parse(args[i + 3]);
                        allPeople.get(id).setSex(args[i + 2].equals("м") ? Sex.MALE : Sex.FEMALE);
                        allPeople.get(id).setName(name);
                        allPeople.get(id).setBirthDate(date);
                        System.out.println(allPeople.size());
                    }
                }
                break;
            case "-d":
                synchronized (allPeople) {
                    for (int i = 1; args.length > i; i++) {
                        int id = Integer.parseInt(args[i]);
                        allPeople.get(id).setBirthDate(null);
                        allPeople.get(id).setSex(null);
                        allPeople.get(id).setName(null);
                    }
                }
                break;
            case "-i":
                synchronized (allPeople) {
                    for (int i = 1; args.length > i; i++) {
                        int id = Integer.parseInt(args[i]);
                        String name = allPeople.get(id).getName();
                        String sex = allPeople.get(id).getSex().equals(Sex.MALE) ? "м" : "ж";
                        String date = simpleDateFormat2.format(allPeople.get(id).getBirthDate());
                        System.out.println(name + " " + sex + " " + date);
                    }
                }
                break;
        }
    }
}
