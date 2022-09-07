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
        Integer nod = NOD(i1, i2);

        while (nod != 0) {
            nod = NOD(i1, i2);
        }
    }

    public static Integer NOD(Integer i1, Integer i2) {
        if (i1 > i2 && i1 % i2 != 0) {
            return Solution.i1 = i1 % i2;
        } else if (i1 > i2 && i1 % i2 == 0) {
            System.out.println(i2);
            return 0;
        } else if (i2 > i1 && i2 % i1 != 0) {
            return Solution.i2 = i2 % i1;
        } else if (i2 > i1 && i2 % i1 == 0) {
            System.out.println(i1);
            return 0;
        }
        System.out.println(i1);
        return 0;
    }
}

