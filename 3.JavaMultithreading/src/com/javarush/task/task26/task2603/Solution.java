package com.javarush.task.task26.task2603;

import java.util.Arrays;
import java.util.Comparator;

/* 
Убежденному убеждать других не трудно
*/

public class Solution {

    public static void main(String[] args) {
        CustomizedComparator comparator = new CustomizedComparator<>();
        CustomizedComparator comparator1 = new CustomizedComparator<>(comparator);
        CustomizedComparator [] comparators  = new CustomizedComparator[2];
        comparators[0] = comparator;
        comparators[1] = comparator1;
        CustomizedComparator customizedComparator = new CustomizedComparator<>(comparators);

    }

    public static class CustomizedComparator<T> implements Comparator<T> {
        private final Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>... comparators) {
            this.comparators = comparators;
        }

        @Override
        public int compare(T o1, T o2) {
            //Arrays.stream(comparators).forEach(c -> c.compare(o1,o2));
            return Arrays.stream(comparators).mapToInt(c -> c.compare(o1, o2)).filter(res -> res != 0).findFirst().orElse(0);
        }
    }
}
