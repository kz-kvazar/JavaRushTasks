package com.javarush.task.task30.task3008.client;

public class ClientGuiController extends Client {
    private ClientGuiModel model = new ClientGuiModel();
    private ClientGuiView view = new ClientGuiView(this);

    public static void main(String[] args) {
        ClientGuiController controller = new ClientGuiController();
        controller.run();
    }

    public ClientGuiModel getModel() {
        return model;
    }

    protected SocketThread getSocketThread() {
        return new GuiSocketThread();
    }

    public void run() {
        getSocketThread().run();
    }

    public String getServerAddress() {
        return view.getServerAddress();
    }

    public int getServerPort() {
        return view.getServerPort();
    }

    public String getUserName() {
        return view.getUserName();
    }

    public class GuiSocketThread extends SocketThread {
        @Override
        protected void informAboutAddingNewUser(String userName) {
            model.addUser(userName);
            view.refreshUsers();
        }

        @Override
        protected void informAboutDeletingNewUser(String userName) {
            model.deleteUser(userName);
            view.refreshUsers();
        }

        @Override
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            view.notifyConnectionStatusChanged(clientConnected);
        }


        @Override
        protected void processIncomingMessage(String message) {
            model.setNewMessage(message);
            view.refreshMessages();
        }
    }
}
