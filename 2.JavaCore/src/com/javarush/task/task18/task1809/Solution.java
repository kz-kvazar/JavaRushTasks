package com.javarush.task.task18.task1809;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Реверс файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();

        try (FileInputStream fis = new FileInputStream(file1); FileOutputStream fos = new FileOutputStream(file2)) {
            List<Integer> bytes = new ArrayList<>();
            while (fis.available() > 0) {
                bytes.add(fis.read());
            }
            Collections.reverse(bytes);
            for (Integer aByte : bytes) {
                fos.write(aByte);
            }
        }
    }
}
