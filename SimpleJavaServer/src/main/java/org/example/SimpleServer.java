package org.example;

import org.example.request.RequestProcessor;

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
            try (Socket socket = serverSocket.accept()) {
                System.out.println("Just connected to " + socket.getRemoteSocketAddress());
                processInput(socket);
                System.out.println("Request handling finished successfully");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void processInput(Socket socket) throws IOException {
        HttpRequest httpRequest = new HttpRequest(parseHttpRequestString(socket));
        RequestProcessor requestProcessor = new RequestProcessor(socket, httpRequest);
        requestProcessor.process();
    }

    private String parseHttpRequestString(Socket socket) throws IOException {
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
