package ru.ifmo.db.domain;

import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    ClientThread(Socket socket){
        this.socket = socket;
        this.start();
    }
    public void run(){

    }
}
