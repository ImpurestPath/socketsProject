package ru.ifmo.db.gui;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class domainClient {
    domainClient(){
        int serverPort = 3567; // здесь обязательно нужно указать порт к которому привязывается сервер.
        String address = "127.0.0.1"; // это IP-адрес компьютера, где исполняется наша серверная программа.
        // Здесь указан адрес того самого компьютера где будет исполняться и клиент.

        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            System.out.println("Any of you heard of a socket with IP address " + address + " and port " + serverPort + "?");
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Yes! I just got hold of the program.");

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            final ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            final ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();

            //while (true) {
            out.writeObject(DomainCommands.ADD_FILM);
            out.flush();
            //out.writeObject();
            out.flush(); // заставляем поток закончить передачу данных.
            System.out.println(in.readObject().toString());
            System.out.println("Looks like the server is pleased with us. Go ahead and enter more lines.");
            System.out.println();
            //}
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
