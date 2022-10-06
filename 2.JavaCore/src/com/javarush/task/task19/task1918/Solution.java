package com.javarush.task.task19.task1918;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
Знакомство с тегами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fileReader = new BufferedReader(new FileReader(console.readLine()))) {
            while (fileReader.ready()) {
                stringBuilder.append(fileReader.readLine());
            }

            String fileContent = stringBuilder.toString().replaceAll("[\\r\\n]+", "");
            String tag = args[0];
            Document document = Jsoup.parse(fileContent);
            Elements element = document.select(tag);
            for (Element e : element) {
                System.out.println(e);
            }
        }
    }
}
