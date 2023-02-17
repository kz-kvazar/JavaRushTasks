package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;

import java.nio.file.Paths;
import java.util.Scanner;

public class Archiver {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите полный путь куда сохранять архив");
        String pathTo = scanner.nextLine();

        ZipFileManager zipFileManager = new ZipFileManager(Paths.get(pathTo));

        System.out.println("Введите путь к файлу, который будем архивировать");
        String pathFrom = scanner.nextLine();

        zipFileManager.createZip(Paths.get(pathFrom));

        ExitCommand exitCommand = new ExitCommand();
        exitCommand.execute();
    }
}
