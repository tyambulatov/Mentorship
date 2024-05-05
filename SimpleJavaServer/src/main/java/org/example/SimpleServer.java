package org.example;

import org.example.request.RequestProcessor;
import org.example.util.UtilMethods;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer extends Thread {

    ServerSocket serverSocket;

    public SimpleServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Server: start");
                processInput(socket);
                System.out.println("Server: finish");
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void processInput(Socket socket) throws IOException {
        HttpRequest httpRequest = new HttpRequest(UtilMethods.parseHttpRequestString(socket));
        RequestProcessor requestProcessor = new RequestProcessor(socket, httpRequest);
        requestProcessor.process();
    }


}
