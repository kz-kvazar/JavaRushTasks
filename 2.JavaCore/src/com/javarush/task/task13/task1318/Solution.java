package com.javarush.task.task13.task1318;

import java.io.*;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String a = reader.readLine();
        reader.close();
        File file = new File(a);

        FileInputStream f = new FileInputStream(file);

        int i;
        while((i=f.read())!= -1){

            System.out.print((char)i);
        }

        f.close();

    }
}