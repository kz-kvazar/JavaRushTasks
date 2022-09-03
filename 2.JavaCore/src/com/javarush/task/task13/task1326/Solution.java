package com.javarush.task.task13.task1326;

import java.io.*;
import java.util.*;

/* 
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String filePath = bufferedReader.readLine();
        bufferedReader.close();

        FileInputStream fileInputStream = new FileInputStream(filePath);
        Scanner scanner =new Scanner(new InputStreamReader(fileInputStream));
        //int i;
        ArrayList<Integer> arr = new ArrayList<>();
        while ( scanner.hasNext()){
            arr.add(scanner.nextInt());
        }
        fileInputStream.close();
        arr.stream().filter(x -> x % 2 == 0 ).sorted(Comparator.comparingInt(x -> x)).forEach(System.out::println);
    }
}
