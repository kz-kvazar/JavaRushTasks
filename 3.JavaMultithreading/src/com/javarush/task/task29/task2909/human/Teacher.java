package com.javarush.task.task29.task2909.human;

public class Teacher extends Human {
    protected int course;
    private int numberOfStudents;
    private String university;

    public Teacher(String name, int age, int numberOfStudents) {
        super(name,age);
        this.numberOfStudents = numberOfStudents;
    }

    public void live() {
        teach();
    }

    public void teach() {
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void printData() {
        System.out.println("Преподаватель: " + name);
    }

    public int getCourse() {
        return course;
    }
}