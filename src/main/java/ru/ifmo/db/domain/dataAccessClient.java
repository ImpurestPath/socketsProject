package ru.ifmo.db.domain;

import ru.ifmo.db.dataAccess.DataAccessCommands;
import ru.ifmo.db.dataAccess.DTO.FilmDTO;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class dataAccessClient {
    private ObjectInputStream in;
    private ObjectOutputStream out;

    dataAccessClient() {
        int serverPort = 3567; // здесь обязательно нужно указать порт к которому привязывается сервер.
        String address = "127.0.0.1"; // это IP-адрес компьютера, где исполняется наша серверная программа.
        // Здесь указан адрес того самого компьютера где будет исполняться и клиент.
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, serverPort);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

}
