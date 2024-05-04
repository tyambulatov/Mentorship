package org.example.simpleClient;

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
            System.out.println(parseHttpResponseString(socket));
            System.out.println("Client: finish");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        }
    }

    private void sendRequest(Socket socket, String httpRequest) throws IOException {
        System.out.println("Client send request start");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write(httpRequest);
        bufferedWriter.flush();
        System.out.println("Client send request finished");

    }

    private String parseHttpResponseString(Socket socket) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = socket.getInputStream();
        final byte[] buffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer, 0, Math.min(inputStream.available(), 2048))) > 0) {
            String data = new String(buffer, 0, bytesRead);
            stringBuilder.append(data);
        }

        return stringBuilder.toString();
    }
}
