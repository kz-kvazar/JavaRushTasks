package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        //String fileName = urlString.substring(urlString.lastIndexOf('/') + 1);
        String fileName = Paths.get(urlString).getFileName().toString();
        URL url = new URL(urlString);
        downloadDirectory = downloadDirectory.resolve(fileName);

        InputStream inputStream = url.openStream();

        Path temp = Files.createTempFile(null,null);
        Files.copy(inputStream,temp,StandardCopyOption.REPLACE_EXISTING);
        
        Files.move(temp,downloadDirectory);
        return downloadDirectory;
    }
}
