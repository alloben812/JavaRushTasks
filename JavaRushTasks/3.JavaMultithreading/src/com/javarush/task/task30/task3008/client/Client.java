package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

import static com.javarush.task.task30.task3008.MessageType.NAME_ACCEPTED;
import static com.javarush.task.task30.task3008.MessageType.NAME_REQUEST;


/**
 * Created by NazarenkoDS on 15.05.2017.
 */
public class Client {

    protected Connection connection;
    private volatile boolean clientConnected = false;


    protected String getServerAddress(){
        ConsoleHelper.writeMessage("Введите адрес сервера:");
        return ConsoleHelper.readString();
    }
    protected int getServerPort(){
        ConsoleHelper.writeMessage("Введите порт:");
        return ConsoleHelper.readInt();
    }

    protected String getUserName(){
        ConsoleHelper.writeMessage("Введите имя пользователя:");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole(){
        return true;
    }

    protected SocketThread getSocketThread(){
        return new SocketThread();
    }

    protected void sendTextMessage(String text){
        try {
            connection.send(new Message(MessageType.TEXT,text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Что-то не так с вашим сообщением" );
            clientConnected = false;
        }
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Что-то не так");
            }
        }
        if (clientConnected) System.out.println("Соединение установлено. Для выхода наберите команду ‘exit’.");
        else System.out.println("Произошла ошибка во время работы клиента.");

        String s = null;
        while (clientConnected) {
            s = ConsoleHelper.readString();
            if (s.equals("exit")) break;
            if (shouldSendTextFromConsole()) sendTextMessage(s);
        }
    }

    public class SocketThread extends Thread{
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }
        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage("Участник с именем "+ userName + " присоединился к чату");
        }

        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage("Участник с именем "+ userName + " покинул чат");
        }
        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException{
            while(true){
                Message message = connection.receive();
                if(message !=null)
                if(message.getType() == NAME_REQUEST) connection.send(new Message(MessageType.USER_NAME,getUserName()));
                else
                    if(message.getType() == NAME_ACCEPTED) {notifyConnectionStatusChanged(true);return;}
                    else
                        throw new IOException("Unexpected MessageType");
                else throw new IOException("Unexpected MessageType");
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            while (true){
                Message message = connection.receive();
                if(message!=null)
                    if(message.getType()==MessageType.TEXT) processIncomingMessage(message.getData());
                    else
                        if(message.getType()==MessageType.USER_ADDED) informAboutAddingNewUser(message.getData());
                        else
                            if(message.getType() == MessageType.USER_REMOVED) informAboutDeletingNewUser(message.getData());
                            else throw new IOException("Unexpected MessageType");
                 else throw new IOException("Unexpected MessageType");
            }
        }

        @Override
        public void run() {
            try {
                connection = new Connection(new Socket(getServerAddress(),getServerPort()));
                clientHandshake();
                clientMainLoop();
            } catch (IOException e) {
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }


        }
    }

    public static void main(String[] args){
        Client client = new Client();
        client.run();
    }
}
