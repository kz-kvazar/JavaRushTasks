package com.javarush.task.task18.task1827;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

/*
Прайсы
*/

public class Solution {


    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        int maxId = 0;
        String read;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                read = fileReader.readLine();
                int id = Integer.parseInt(read.substring(0, 8).trim());
                if (id > maxId) {
                    maxId = id;
                }
            }
        }

        switch (args[0]) {
            case "-c":
                String name = "";

                for (int i = 1; i < args.length - 2; i++) {
                    name += args[i];
                }
                if (name.length() > 30) {
                    name = name.substring(0, 30);
                }
                String price = args[args.length - 2];
                if (price.length() > 8) {
                    price = price.substring(0, 8);
                }
                String quantity = args[args.length - 1];
                if (quantity.length() > 4) {
                    quantity = quantity.substring(0, 4);
                }
                try (FileWriter fileWriter = new FileWriter(fileName, true)) {
                    fileWriter.write("\n");
                    fileWriter.write(String.format("%-8d%-30s%-8s%-4s", ++maxId, name, price, quantity));
                }
        }
    }
}
