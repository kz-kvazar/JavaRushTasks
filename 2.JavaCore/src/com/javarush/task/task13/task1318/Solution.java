package com.javarush.task.task13.task1318;

import java.io.*;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        Scanner sc = new Scanner(System.in);
        String b = sc.nextLine();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //String a = reader.readLine();
        reader.close();
        File file = new File(b);

        FileInputStream f = new FileInputStream(file);

        int i;
        while((i=f.read())!= -1){

            System.out.print((char)i);
        }

        f.close();


//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String sourceFileName = reader.readLine();
//        FileInputStream fileInputStream = new FileInputStream(sourceFileName);
//
//        Scanner scanner = new Scanner(fileInputStream);
//        StringBuilder builder = new StringBuilder();
//
//        while (scanner.hasNextLine()) {
//            builder.append(scanner.nextLine()).append("\n");
//        }
//
//        System.out.print(builder.toString());
//
//        scanner.close();
//        reader.close();


    }
}