package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }
        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage(String.format("Участник с именем %s присоединился к чату", userName));
        }
        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(String.format("Участник с именем %s покинул чат", userName));
        }
        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this){
                Client.this.notify();
            }
        }
    }

    protected String getServerAddress() {
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Ошибка отправки сообщения");
            clientConnected = false;
        }
    }
    public void run(){
        SocketThread socketThread = getSocketThread();
        // Помечаем поток как daemon
        socketThread.setDaemon(true);
        socketThread.start();
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
            return;
        }
        while (clientConnected) {
            String console = ConsoleHelper.readString();
            if (console.equals("exit")) {
                break;
            }
            if (shouldSendTextFromConsole()) {
                sendTextMessage(console);
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        client.run();
    }
}
