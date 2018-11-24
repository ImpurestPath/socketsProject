package ru.ifmo.db.dataAccess;



import ru.ifmo.db.dataAccess.SQL.SQLConnection;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    Server() {
        ConnectionDAO connection = new SQLConnection();
        int port = 3567; // случайный порт (может быть любое число от 1025 до 65535)
        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Waiting for a client...");
            while (true){
                Socket client = null;
                while (client == null) {
                    client = ss.accept();
                }
                Thread t = new ClientThread(client,connection);
                t.run();
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
