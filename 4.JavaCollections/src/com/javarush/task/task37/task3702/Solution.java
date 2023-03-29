package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.male.MaleFactory;

public class Solution {
    public static void main(String[] args) {
        MaleFactory maleFactory = new MaleFactory();
        Human man = maleFactory.getPerson(99);
        Human kid = maleFactory.getPerson(4);
        Human teen = maleFactory.getPerson(15);

        System.out.println(man);
        System.out.println(kid);
        System.out.println(teen);
    }
}
