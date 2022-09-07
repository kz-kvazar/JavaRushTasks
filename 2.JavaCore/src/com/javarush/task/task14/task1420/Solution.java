package com.javarush.task.task14.task1420;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
НОД
*/

public class Solution {
    public static Integer i1;
    public static Integer i2;

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        i1 = Integer.valueOf(bufferedReader.readLine());
        i2 = Integer.valueOf(bufferedReader.readLine());
        bufferedReader.close();
        System.out.println(NOD(i1, i2));



    }

    public static Integer NOD(Integer a, Integer b) {
        while (!a.equals(b)){
            if (a > b) {
                a = a - b;
                System.out.println("A is "+a);
            }
            else  {
                b = b - a;
                System.out.println("B is " +b);
            }
        }
        return a;
}
}

