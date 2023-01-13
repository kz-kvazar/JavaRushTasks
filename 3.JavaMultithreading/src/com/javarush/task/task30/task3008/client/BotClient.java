package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);


            if (!message.contains(": ")) {
                return;
            }
            String[] name = message.split(": ");

            if (name[1].equals("дата")) {
                BotClient.this.sendTextMessage("Информация для " + name[0] + ": " + new SimpleDateFormat("d.MM.YYYY").format(Calendar.getInstance().getTime()));
            } else if (name[1].equalsIgnoreCase("день")) {
                BotClient.this.sendTextMessage("Информация для " + name[0] + ": " + new SimpleDateFormat("d").format(Calendar.getInstance().getTime()));
            } else if (name[1].equalsIgnoreCase("месяц")) {
                BotClient.this.sendTextMessage("Информация для " + name[0] + ": " + new SimpleDateFormat("MMMM").format(Calendar.getInstance().getTime()));
            } else if (name[1].equalsIgnoreCase("год")) {
                BotClient.this.sendTextMessage("Информация для " + name[0] + ": " + new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime()));
            } else if (name[1].equalsIgnoreCase("время")) {
                BotClient.this.sendTextMessage("Информация для " + name[0] + ": " + new SimpleDateFormat("H:mm:ss").format(Calendar.getInstance().getTime()));
            } else if (name[1].equalsIgnoreCase("час")) {
                BotClient.this.sendTextMessage("Информация для " + name[0] + ": " + new SimpleDateFormat("H").format(Calendar.getInstance().getTime()));
            } else if (name[1].equalsIgnoreCase("минуты")) {
                BotClient.this.sendTextMessage("Информация для " + name[0] + ": " + new SimpleDateFormat("m").format(Calendar.getInstance().getTime()));
            } else if (name[1].equalsIgnoreCase("секунды")) {
                BotClient.this.sendTextMessage("Информация для " + name[0] + ": " + new SimpleDateFormat("s").format(Calendar.getInstance().getTime()));
            }
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    public static void main(String[] args) {
        new BotClient().run();
    }
}
