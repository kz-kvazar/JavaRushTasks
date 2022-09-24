package com.javarush.task.task18.task1808;

import java.io.*;

/* 
Разделение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();

        try (FileInputStream fis = new FileInputStream(file1);
             FileOutputStream fos1 = new FileOutputStream(file2);
             FileOutputStream fos2 = new FileOutputStream(file3)){

            byte[] readBytes = new byte[fis.available()];
            int bytesCount = fis.read(readBytes);

            fos1.write(readBytes,0,(bytesCount+bytesCount%2)/2);
            fos2.write(readBytes,(bytesCount+bytesCount%2)/2,bytesCount/2);

        }
    }
}
