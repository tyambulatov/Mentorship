package org.example;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Throwable {
        int port = 8080;
        new Thread(new SimpleServer(port)).start();
    }
}
