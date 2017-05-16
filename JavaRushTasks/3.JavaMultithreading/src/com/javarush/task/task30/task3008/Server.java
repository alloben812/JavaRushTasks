package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.javarush.task.task30.task3008.ConsoleHelper.readInt;
import static com.javarush.task.task30.task3008.ConsoleHelper.writeMessage;

/**
 * Created by NazarenkoDS on 12.05.2017.
 */
public class Server {
    public static void main(String[]args) {
        int port = readInt();
        try (ServerSocket serverSocket = new ServerSocket(port);){
            writeMessage("Сервер запущен");
            while (true){
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (Exception e) {
            writeMessage(e.getMessage());
        }
    }
    private static class Handler extends Thread{
        private Socket socket;

        public Handler(Socket socket){
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if(message.getType() == MessageType.USER_NAME){
                    if(!message.getData().isEmpty()){
                        if(!connectionMap.containsKey(message.getData())){
                            connectionMap.put(message.getData(),connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return message.getData();
                        }
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException{
            for(Map.Entry<String,Connection> pair : connectionMap.entrySet()){
                if(!userName.equals(pair.getKey())){
                    connection.send(new Message(MessageType.USER_ADDED,pair.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true){
                Message answer = connection.receive();
                if(answer.getType()!=null&&answer.getType() == MessageType.TEXT){
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName +": "+ answer.getData()));
                }
                else writeMessage("Message type is not TEXT");
            }
        }

        @Override
        public void run() {
            writeMessage("Установлено соединение с удаленным сервером:" + socket.getRemoteSocketAddress());
            String userName = null;
            try(Connection connection = new Connection(socket)) {
                SocketAddress socketAddress = connection.getRemoteSocketAddress();
                writeMessage("Установлено новое соединение с удаленным адресом по порту: " + socketAddress);
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection,userName);
                serverMainLoop(connection,userName);

            } catch (IOException e) {
                writeMessage(e.getMessage());
            }
            catch (ClassNotFoundException e1){
                writeMessage(e1.getMessage());
            }
            if (userName != null&&connectionMap.containsKey(userName)) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
            writeMessage("Соедининие с удаленным сервером закрыто");
        }
    }

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message){
        for(Map.Entry<String,Connection> pair: connectionMap.entrySet()){
            try {
                pair.getValue().send(message);
            } catch (IOException e) {
                writeMessage("Произошла ошибка. Не смогли отправить сообщение "+ pair.getKey());
            }
        }
    }
}
