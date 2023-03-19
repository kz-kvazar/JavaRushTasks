package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

/* 
Десериализация JSON объекта
*/

public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(new FileReader(Paths.get(fileName).toFile()), clazz);
    }
    public static void main(String[] args) {
    }
}
