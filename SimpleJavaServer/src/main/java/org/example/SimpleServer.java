package org.example;

import org.example.request.CompositeRequestProcessor;
import org.example.request.PathRequestRule;
import org.example.request.RequestProcessor;
import org.example.users.UsersRequestProcessor;
import org.example.util.UtilMethods;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class SimpleServer extends Thread {

    ServerSocket serverSocket;

    CompositeRequestProcessor processor = new CompositeRequestProcessor(
            Map.of(
                    new PathRequestRule("users"), new CompositeRequestProcessor(
                            Map.of(
                                    new PathRequestRule("/\\d"), new SingleUserRequestProcessor(),
                                    new PathRequestRule(""), new UsersRequestProcessor()
                            )
                    ),
                    new PathRequestRule("products"), new CompositeRequestProcessor(Map.of())
            )
    );

//    users/123/cart/1


    public SimpleServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Request: start");
                processInput(socket);
                System.out.println("Request: finish");
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void processInput(Socket socket) throws IOException {
        HttpRequest httpRequest = new HttpRequest(UtilMethods.parseHttpRequestString(socket));
        RequestProcessor requestProcessor = new RequestProcessor(socket, httpRequest);
        processor.process(httpRequest);
        requestProcessor.process();
    }


}
