package com.javarush.task;

import javax.sound.sampled.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;

public class Modbus {
    public static int regulatePower = 10;
    public static Clip clip;
    static KGY kgy;
    static long now = new Date().getTime();

    public static void main(String[] args) throws InterruptedException {

        String host = "10.70.0.28"; // IP-адрес устройства
        int port = 502; // Порт Modbus
        int transactionId = 123; // ID транзакции
        int protocolId = 0; // ID протокола (нули для Modbus/TCP)
        int dataLength = 6; // Длина данных в пакете (байты)
        int slaveAddress = 1; // Адрес устройства
        int functionCode = 4; // Код функции чтения регистров
        int startRegister = 0; // Начальный регистр чтения
        int numRegisters = 12; // Количество регистров

        alarm(1);

        kgy = new KGY();
        Thread.sleep(100);

        // Формирование запроса Modbus
        byte[] request = {(byte) (transactionId >> 8), // Старший байт ID транзакции
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


        try (Socket socket = new Socket(host, port); DataInputStream inputStream = new DataInputStream(socket.getInputStream()); DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {

            while (true) {
                outputStream.write(request);
                outputStream.flush();
                Thread.sleep(200);

                inputStream.skip(9);

                byte[] read = new byte[inputStream.available()];
                int bytesCount = inputStream.read(read);

                logger(read);

                Thread.sleep(2000);

                clearConsole();

            }

        } catch (IOException | InterruptedException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println(e);
            alarm(3);
        }

    }

    public static void operate(float kpa) throws InterruptedException {
        if ((kpa > 5.5 || kpa < 4.5) && kpa > 0.2) {

            // Определение кода ANSI для красного цвета
            final String ANSI_RED = "\u001B[31m";
            // Определение кода ANSI для сброса цвета
            final String ANSI_RESET = "\u001B[0m";

            int powerConstant = kgy.getPowerConstant();
            int actualPower = kgy.getActualPower();

            if (kpa > 5.5 && (new Date().getTime() - now) >= 20_000 && (powerConstant - actualPower) <= 50) {
                System.out.println(ANSI_RED + "Давление перед ОП выше нормы: " + Math.round(kpa * 100) / 100.0 + " kPa.");
                if (powerConstant <= 1540){
                    kgy.setPowerConstant((short) (powerConstant + regulatePower));
                    System.out.println("Увеличиваем мощность на " + regulatePower + " кВт" + ANSI_RESET);
                    //alarm(1);
                }
                now = new Date().getTime();
                Thread.sleep(2000);
            } else if (kpa > 4 && kpa < 6 && (new Date().getTime() - now) >= 20_000 && (powerConstant - actualPower) <= 50) {
                System.out.println(ANSI_RED + "Давление перед ОП ниже нормы: " + Math.round(kpa * 100) / 100.0 + " kPa.");
                if (powerConstant >= 820){
                    kgy.setPowerConstant((short) (powerConstant - regulatePower));
                    System.out.println("Уменьшаем мощность на " + regulatePower + " кВт" + ANSI_RESET);
                    //alarm(1);
                }
                now = new Date().getTime();
                Thread.sleep(2000);
            } else if (kpa < 4 && (new Date().getTime() - now) >= 7_000) {
                System.out.println(ANSI_RED + "Критическое давление перед ОП: " + Math.round(kpa * 100) / 100.0 + " kPa." );
                if (powerConstant >= 900){
                    kgy.setPowerConstant((short) (powerConstant - 100));
                    System.out.println("Уменьшаем мощность на 100 кВт" + ANSI_RESET);
                }
                now = new Date().getTime();
                alarm(3);
                Thread.sleep(2000);
            }
        } else {
            System.out.println("Давление перед ОП " + Math.round(kpa * 100) / 100.0 + " kPa");
        }
    }

    public static void alarm(int times) {
        File soundFile = new File("C:\\Windows\\Media\\" + "Windows Background.wav");
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile)) {

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(times);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ignored) {
            System.out.println(ignored);
        }

    }

    public static void logger(byte[] read) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(read).order(ByteOrder.BIG_ENDIAN);
        float op = byteBuffer.getFloat();
        System.out.println("Напор конденсата " + (int) byteBuffer.getFloat() + " mPa");
        System.out.println("Давление перед GTS " + (int) byteBuffer.getFloat() + " kPa");
        System.out.println("Давление после GTS " + (int) byteBuffer.getFloat() + " kPa");
        System.out.println("СН4 ВНС-1 " + (int) byteBuffer.getFloat() + " %");
        System.out.println("СН4 ВНС-2 " + (int) byteBuffer.getFloat() + " %");
        operate(op);
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
        } catch (final Exception ignored) {
            System.out.println(ignored);
            alarm(3);
        }
    }

    public static class KGY {
        DataInputStream inputStream;
        DataOutputStream outputStream;
        byte[] request;
        byte[] setPower;
        String host = "172.30.40.50"; // IP-адрес устройства
        int port = 502; // Порт Modbus
        int transactionId = 123; // ID транзакции
        int protocolId = 0; // ID протокола (нули для Modbus/TCP)
        int dataLength = 6; // Длина данных в пакете (байты)
        int slaveAddress = 1; // Адрес устройства

        public int getPowerConstant() {
            request = new byte[]{(byte) (transactionId >> 8), // Старший байт ID транзакции
                    (byte) (transactionId & 0xFF), // Младший байт ID транзакции
                    (byte) (protocolId >> 8), // Старший байт ID протокола
                    (byte) (protocolId & 0xFF), // Младший байт ID протокола
                    (byte) (dataLength >> 8), // Старший байт длины данных
                    (byte) (dataLength & 0xFF), // Младший байт длины данных
                    (byte) slaveAddress, // Адрес устройства
                    (byte) 3, // Код функции
                    (byte) (8639 >> 8), // Старший байт начального регистра
                    (byte) (8639 & 0xFF), // Младший байт начального регистра
                    (byte) (1 >> 8), // Старший байт количества регистров
                    (byte) (1 & 0xFF) // Младший байт количества регистров
            };
            try (Socket socket2 = new Socket(host, port); DataInputStream inputStream = new DataInputStream(socket2.getInputStream()); DataOutputStream outputStream = new DataOutputStream(socket2.getOutputStream())) {
                outputStream.write(request);
                outputStream.flush();

                Thread.sleep(200);

                long skipped = inputStream.skip(9);
                short aShort = inputStream.readShort();

                byte[] result = new byte[inputStream.available()];
                int bytesCount = inputStream.read(result);
                return aShort;
            } catch (IOException | InterruptedException ignored) {
                alarm(3);
                System.out.println("\u001B[31m" + "КГУ не отвечает. Регулирование невозможно!" + "\u001B[0m");
            }

            return 1000;
        }

        public void setPowerConstant(short power) {
            setPower = new byte[]{(byte) (transactionId >> 8), // Старший байт ID транзакции
                    (byte) (transactionId & 0xFF), // Младший байт ID транзакции
                    (byte) (protocolId >> 8), // Старший байт ID протокола
                    (byte) (protocolId & 0xFF), // Младший байт ID протокола
                    (byte) (dataLength >> 8), // Старший байт длины данных
                    (byte) (dataLength & 0xFF), // Младший байт длины данных
                    (byte) slaveAddress, // Адрес устройства
                    (byte) 16, // Код функции
                    (byte) (8639 >> 8), // Старший байт начального регистра
                    (byte) (8639 & 0xFF), // Младший байт начального регистра
                    (byte) (1 >> 8), // Старший байт количества регистров для записи
                    (byte) (1 & 0xFF), // Младший байт количества регистров для записи
                    (byte) (2), // колличество байт данных
                    (byte) (power >> 8), // Младший байт заданного значения
                    (byte) (power & 0xFF) // Младший байт заданного значения
            };
            try (Socket socket2 = new Socket(host, port); DataInputStream inputStream = new DataInputStream(socket2.getInputStream()); DataOutputStream outputStream = new DataOutputStream(socket2.getOutputStream())) {
                outputStream.write(setPower);
                outputStream.flush();

                Thread.sleep(200);
                byte[] result = new byte[inputStream.available()];
                int bytesCount = inputStream.read(result);
            } catch (IOException | InterruptedException ignored) {
                System.out.println(ignored);
                alarm(3);
            }

        }

        public int getActualPower() {
            request = new byte[]{(byte) (transactionId >> 8), // Старший байт ID транзакции
                    (byte) (transactionId & 0xFF), // Младший байт ID транзакции
                    (byte) (protocolId >> 8), // Старший байт ID протокола
                    (byte) (protocolId & 0xFF), // Младший байт ID протокола
                    (byte) (dataLength >> 8), // Старший байт длины данных
                    (byte) (dataLength & 0xFF), // Младший байт длины данных
                    (byte) slaveAddress, // Адрес устройства
                    (byte) 3, // Код функции
                    (byte) (8202 >> 8), // Старший байт начального регистра
                    (byte) (8202 & 0xFF), // Младший байт начального регистра
                    (byte) (1 >> 8), // Старший байт количества регистров
                    (byte) (1 & 0xFF) // Младший байт количества регистров
            };
            try (Socket socket2 = new Socket(host, port); DataInputStream inputStream = new DataInputStream(socket2.getInputStream()); DataOutputStream outputStream = new DataOutputStream(socket2.getOutputStream())) {
                outputStream.write(request);
                outputStream.flush();

                Thread.sleep(200);

                long skipped = inputStream.skip(9);
                short aShort = inputStream.readShort();

                byte[] result = new byte[inputStream.available()];
                int bytesCount = inputStream.read(result);
                return aShort;
            } catch (IOException | InterruptedException ignored) {
                alarm(3);
                System.out.println("\u001B[31m" + "КГУ не отвечает. Регулирование невозможно!" + "\u001B[0m");
                System.out.println(ignored);
            }

            return 1000;
        }

    }
}

