package com.javarush.task.task28.task2811;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedHashMap;

/* 
ReentrantReadWriteLock
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        ReadWriteMap<Integer, String> linkedSafeMap = new ReadWriteMap<>(new LinkedHashMap<>());
        Document page = Jsoup.connect("https://etnosvit.com/anekdoty.html")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
        Elements elements = page.select("[class=sue-panel-content sue-content-wrap]");
        //
        for (int i = 3; i < 5; i++) {
            Element element = elements.get(i);
            System.out.println(element.text());
        }
    }
}
