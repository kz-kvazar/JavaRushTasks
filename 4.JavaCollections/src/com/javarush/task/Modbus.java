package com.javarush.task;

import javax.sound.sampled.*;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Modbus {
    public static Clip clip;
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {

        String host = "10.70.0.28"; // IP-адрес устройства
        int port = 502; // Порт Modbus
        int transactionId = 123; // ID транзакции
        int protocolId = 0; // ID протокола (нули для Modbus/TCP)
        int dataLength = 6; // Длина данных в пакете (байты)
        int slaveAddress = 1; // Адрес устройства
        int functionCode = 4; // Код функции чтения регистров
        int startRegister = 0; // Начальный регистр чтения
        int numRegisters = 12; // Количество регистров

            File soundFile = new File("C:\\Windows\\Media\\" + "Windows Background.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();


        // Формирование запроса Modbus
        byte[] request = {
                (byte) (transactionId >> 8), // Старший байт ID транзакции
                (byte) (transactionId & 0xFF), // Младший байт ID транзакции
                (byte) (protocolId >> 8), // Старший байт ID протокола
                (byte) (protocolId & 0xFF), // Младший байт ID протокола
                (byte) (dataLength >> 8), // Старший байт длины данных
                (byte) (dataLength & 0xFF), // Младший байт длины данных
                (byte) slaveAddress, // Адрес устройства
                (byte) functionCode, // Код функции
                (byte) (startRegister >> 8), // Старший байт начального регистра
                (byte) (startRegister & 0xFF), // Младший байт начального регистра
                (byte) (numRegisters >> 8), // Старший байт количества регистров
                (byte) (numRegisters & 0xFF) // Младший байт количества регистров
        };

        try (Socket socket = new Socket(host, port);
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {

            // Отправка запроса
            outputStream.write(request);
            outputStream.flush();

            // Получение ответа
        while (true){
                outputStream.write(request);
                outputStream.flush();

                inputStream.skip(9);

                byte[] read = new byte[inputStream.available()];
                int bytesCount = inputStream.read(read);
                //System.out.println(read);

                if (bytesCount != (numRegisters * 2)) {
                    System.err.println("Ошибка чтения ответа Modbus");
                    return;
                }

                logger(read);
                Thread.sleep(1500);
                clearConsole();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//        try ( Socket gatewaySocket = new Socket("10.70.0.254", 443);
//                Socket socket2 = new Socket("172.30.40.50", 502);
//             DataInputStream inputStream = new DataInputStream(socket2.getInputStream());
//             DataOutputStream outputStream = new DataOutputStream(socket2.getOutputStream())) {
//
//            byte[] request = {
//                    (byte) (transactionId >> 8), // Старший байт ID транзакции
//                    (byte) (transactionId & 0xFF), // Младший байт ID транзакции
//                    (byte) (protocolId >> 8), // Старший байт ID протокола
//                    (byte) (protocolId & 0xFF), // Младший байт ID протокола
//                    (byte) (dataLength >> 8), // Старший байт длины данных
//                    (byte) (dataLength & 0xFF), // Младший байт длины данных
//                    (byte) slaveAddress, // Адрес устройства
//                    (byte) 3, // Код функции
//                    (byte) (8202 >> 8), // Старший байт начального регистра
//                    (byte) (8202 & 0xFF), // Младший байт начального регистра
//                    (byte) (1 >> 8), // Старший байт количества регистров
//                    (byte) (1 & 0xFF) // Младший байт количества регистров
//            };
//
//            outputStream.write(request);
//            outputStream.flush();
//            long skipped = inputStream.skip(9);
//            byte[] result = new byte[inputStream.available()];
//
//            short readShort = inputStream.readShort();
//
//            System.out.println(readShort);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    public static void alarm(float kpa) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (kpa > 7 || kpa < 4 || kpa < 0.2){
            File soundFile = new File("C:\\Windows\\Media\\" + "Windows Background.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            // Определение кода ANSI для красного цвета
            final String ANSI_RED = "\u001B[31m";
            // Определение кода ANSI для сброса цвета
            final String ANSI_RESET = "\u001B[0m";

            System.out.println(kpa > 7 ? ANSI_RED + "Давление перед ОП выше нормы: " + Math.round(kpa * 100) / 100.0 + " kPa" + ANSI_RESET:
                    ANSI_RED + "Давление перед ОП ниже нормы: " + Math.round(kpa * 100) / 100.0 + " kPa" + ANSI_RESET);
        } else {
            System.out.println("Давление перед ОП " + Math.round(kpa * 100) / 100.0  + " kPa");
        }

    }

    public static void logger (byte[] read) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(read).order(ByteOrder.BIG_ENDIAN);
        float op = byteBuffer.getFloat();
        System.out.println("Напор конденсата " + (int) byteBuffer.getFloat() + " mPa");
        System.out.println("Давление перед GTS " + (int) byteBuffer.getFloat() + " kPa");
        System.out.println("Давление после GTS " + (int) byteBuffer.getFloat() + " kPa");
        System.out.println("СН4 ВНС-1 " + (int) byteBuffer.getFloat() + " %");
        System.out.println("СН4 ВНС-2 " + (int) byteBuffer.getFloat() + " %");
        alarm(op);
    }
    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
