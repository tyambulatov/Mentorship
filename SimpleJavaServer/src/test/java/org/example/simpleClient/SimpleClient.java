package org.example.simpleClient;

import org.example.util.UtilMethods;

import java.io.*;
import java.net.Socket;

public class SimpleClient extends Thread {
    private final String domain;
    private final int port;
    private String httpRequest;

    public SimpleClient(String domain, int port, String httpRequest) {
        this.domain = domain;
        this.port = port;
        this.httpRequest = httpRequest;
    }

    public void run() {
//        while (true) {
            try {
                Socket socket = new Socket(domain, port);
                System.out.println("Client: start");
                sendRequest(socket, httpRequest);
                System.out.println(UtilMethods.parseHttpRequestString(socket));
                System.out.println("Client: finish");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//        }
    }

    private void sendRequest(Socket socket, String httpRequest) throws IOException {
        System.out.println("Client: send request start");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write(httpRequest);
        bufferedWriter.flush();
        System.out.println("Client: send request finished");
    }
}
