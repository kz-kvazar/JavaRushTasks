package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }

    static {
        //add your code here - добавьте код тут
        reset();
    }

    public static CanFly result;

    public static void reset() {
        String res;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
                if ((res = bufferedReader.readLine()).equals("helicopter")){
                    result = new Helicopter();
                } else if (res.equals("plane")){
                    result = new Plane(Integer.parseInt(bufferedReader.readLine()));
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
       //add your code here - добавьте код тут
    }
}
