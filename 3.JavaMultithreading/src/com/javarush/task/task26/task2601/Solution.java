package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static Integer[] sort(Integer[] array) {
        final double median = getMedians(array);

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (int) (Math.abs(o1 - median) - Math.abs(o2 - median));
            }
        });
        return array;
    }

    private static double getMedians(Integer[] array) {
        Arrays.sort(array);
        double res;
        int length = array.length;

        if (length % 2 == 1) {
            res = array[length / 2];
        } else {
            res = (array[length / 2 - 1] + array[length / 2]) / 2.;
        }
        return res;
    }
}
