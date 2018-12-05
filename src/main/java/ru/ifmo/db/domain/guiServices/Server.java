package ru.ifmo.db.domain.guiServices;


import ru.ifmo.db.domain.dataAccessServices.Client;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server(Client client) {
        int port = 3568; // случайный порт (может быть любое число от 1025 до 65535)
        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Waiting for a client...");
            while (true) {
                Socket socket = null;
                while (socket == null) {
                    socket = ss.accept();
                }
                Thread t = new ClientThread(socket, client);
                t.run();
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
