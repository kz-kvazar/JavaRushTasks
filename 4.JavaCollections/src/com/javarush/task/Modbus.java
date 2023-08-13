package com.javarush.task;

import javax.sound.sampled.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Modbus {
    private static int maxPower = 1510;
    public static int regulatePower = 10;
    static List<String> list = new ArrayList<>();
    static int index = 0;
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

        kgy = new KGY();

        list.add("───────────█─█─█─█──█─█─█─█\n");
        list.add("──────────█▄█▄█▄█▄▄█▄█▄█▄█\n");
        list.add("▄▄────▄▄▄▄▄▄▄▄██▀██▄\n");
        list.add("█████████████████████\n");
        list.add("─██████████████▀███▀\n");
        list.add("─██████████████\n");
        list.add("─██▀██▀▀▀██▀██\n");
        list.add("██─██─────██─██\n");

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


        try (Socket socket = new Socket(host, port); DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {

            while (true) {
                outputStream.write(request);
                outputStream.flush();
                Thread.sleep(200);

                inputStream.skip(9);

                byte[] read = new byte[inputStream.available()];
                int bytesCount = inputStream.read(read);

                logger(read);

                Thread.sleep(2000);

            }

        } catch (IOException | InterruptedException | LineUnavailableException | UnsupportedAudioFileException e) {
            alarm(3);
            System.out.println(e);
            Thread.sleep(2000);
        }

    }

    public static List<String> getFrame() {
        if (index < 30) {
            for (int j = 0, listSize = list.size(); j < listSize; j++) {
                String s = list.get(j);
                list.set(j, "  " + s);
            }
            index++;
        } else {
            for (int j = 0, listSize = list.size(); j < listSize; j++) {
                list.set(j, " " + list.get(j).replaceAll(" ", ""));
            }
            index = 0;
        }
        return list;
    }

    public static void operate(double kpa, int powerConstant, int actualPower) throws InterruptedException {
        // Определение кода ANSI для красного цвета
        final String ANSI_RED = "\u001B[31m";
        // Определение кода ANSI для сброса цвета
        final String ANSI_RESET = "\u001B[0m";

        if ((kpa > 5.2 || kpa < 4.3) && actualPower > 0 || actualPower > maxPower) {
            if (kpa > 6) {
                if (regulatePower != 20) regulatePower = 20;
            } else {
                if (regulatePower != 10) regulatePower = 10;
            }

            if (kpa > 5.2 && (new Date().getTime() - now) >= 20_000 && (powerConstant - actualPower) <= 50 && powerConstant < maxPower && actualPower < maxPower) {
                if (powerConstant < 1530) {
                    kgy.setPowerConstant((short) (powerConstant + regulatePower));
                    System.out.println(ANSI_RED + "Увеличиваем мощность на " + regulatePower + " кВт" + ANSI_RESET);
                    //alarm(1);
                }
                now = new Date().getTime();
                Thread.sleep(2000);
            } else if ((kpa < 4.3 && kpa > 3.3 && (powerConstant - actualPower) <= 50) || (actualPower > 1560 && (new Date().getTime() - now) >= 20_000)) {
                if (powerConstant >= 900) {
                    kgy.setPowerConstant((short) (powerConstant - (regulatePower)));
                    System.out.println(ANSI_RED + "Уменьшаем мощность на " + (regulatePower) + " кВт" + ANSI_RESET);
                    checkMaxPower(actualPower, powerConstant);
                    //alarm(1);
                }
                now = new Date().getTime();
                Thread.sleep(2000);
            } else if (kpa < 3.3 && (new Date().getTime() - now) >= 20_000) {
                if (powerConstant >= 900) {
                    kgy.setPowerConstant((short) (powerConstant - 100));
                    System.out.println(ANSI_RED + "Уменьшаем мощность на 100 кВт" + ANSI_RESET);
                }
                now = new Date().getTime();
                alarm(3);
                Thread.sleep(2000);
            }
        } else if (actualPower <= 0 && kpa < 1) {
            if (powerConstant != 900) {
                kgy.setPowerConstant((short) 900);
                alarm(3);
                System.out.println(ANSI_RED + " Устанавливаю константу мощности на 900 кВт" + ANSI_RESET);
            } else {
                System.out.println(ANSI_RED + "КГУ остановленна!!!" + ANSI_RESET);
            }
            Thread.sleep(2000);
        }
    }

    private static void checkMaxPower(int actualPower , int powerConstant) {
        if (actualPower > maxPower) {
            maxPower = powerConstant - 10;
            now = new Date().getTime();
        }
    }

    public static void alarm(int times) throws InterruptedException {
        String audioFilePath = ("C:\\Windows\\Media\\" + "tada.wav");
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audioFilePath))) {
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            byte[] buffer = new byte[4096];
            int bytesRead = 0;

            while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                line.write(buffer, 0, bytesRead);
            }
            line.drain();
            line.stop();
            line.close();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("no such file");
        }
    }

    public static void logger(byte[] read) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(read).order(ByteOrder.BIG_ENDIAN);
        double op = Math.round(byteBuffer.getFloat() * 100) / 100.0;
        int powerConstant = kgy.getPowerConstant();
        int actualPower = kgy.getActualPower();
        clearConsole();
        String registr2 = "Напор конденсата " + (int) byteBuffer.getFloat() + " mPa";
        String registr3 = "Давление перед GTS " + (int) byteBuffer.getFloat() + " kPa";
        String registr4 = "Давление после GTS " + (int) byteBuffer.getFloat() + " kPa";
        String registr5 = "СН4 ВНС-1 " + (int) byteBuffer.getFloat() + " %";
        String registr6 = "СН4 ВНС-2 " + (int) byteBuffer.getFloat() + " %";
        System.out.println("\u001B[32m" + "Давление перед ОП " + op + " kPa");
        System.out.println("Уставка мощности: " + powerConstant + "кВт");
        System.out.println("Активная мощность: " + actualPower + "кВт" + "\u001B[0m");
        System.out.println(getFrame());
        operate(op, powerConstant, actualPower);
    }

    public static void clearConsole() throws InterruptedException {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception ignored) {
            alarm(3);
            System.out.println(ignored);
            Thread.sleep(2000);
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

        public int getPowerConstant() throws InterruptedException {
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

                Thread.sleep(100);

                long skipped = inputStream.skip(9);
                short aShort = inputStream.readShort();

                byte[] result = new byte[inputStream.available()];
                int bytesCount = inputStream.read(result);
                return aShort;
            } catch (IOException | InterruptedException ignored) {
                System.out.println("\u001B[31m" + "КГУ не отвечает. Регулирование невозможно!" + "\u001B[0m");
                alarm(3);
                Thread.sleep(2000);
            }

            return 1000;
        }

        public void setPowerConstant(short power) throws InterruptedException {
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

                Thread.sleep(100);
                byte[] result = new byte[inputStream.available()];
                int bytesCount = inputStream.read(result);
            } catch (IOException | InterruptedException ignored) {
                alarm(3);
                System.out.println(ignored);
                Thread.sleep(2000);
            }

        }

        public int getActualPower() throws InterruptedException {
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

                Thread.sleep(100);

                long skipped = inputStream.skip(9);
                short aShort = inputStream.readShort();

                byte[] result = new byte[inputStream.available()];
                int bytesCount = inputStream.read(result);
                return aShort;
            } catch (IOException | InterruptedException ignored) {
                alarm(3);
                System.out.println("\u001B[31m" + "КГУ не отвечает. Регулирование невозможно!" + "\u001B[0m");
                System.out.println(ignored);
                Thread.sleep(2000);
            }

            return 1000;
        }
    }
}

