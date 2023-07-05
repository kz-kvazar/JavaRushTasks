package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        PrepareMyTest annotation = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
        if (annotation != null){
            for (String s : annotation.fullyQualifiedNames()) {
                System.out.println(s);
            }
            return true;
        }
        return false;
    }

    public static boolean printValues(Class c) {
        PrepareMyTest annotation = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
        if (annotation != null){
            for (Class<?> cl : annotation.value()) {
                System.out.println(cl.getSimpleName());
            }
            return true;
        }
        return false;
    }
}
