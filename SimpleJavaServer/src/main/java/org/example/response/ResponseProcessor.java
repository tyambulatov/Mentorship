package org.example.response;

import org.example.HttpRequest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ResponseProcessor {
    BufferedWriter bufferedWriter;
    HttpRequest httpRequest;

    public ResponseProcessor(Socket socket, HttpRequest httpRequest) throws IOException {
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.httpRequest = httpRequest;
    }

    public void getResponse() throws IOException {
        bufferedWriter.write("HTTP/1.1 200 OK\r\n");
        bufferedWriter.write("Content-Type: html\r\n\r\n");
        bufferedWriter.write("<html><body><h1>Hello World! from GET request</h1></body></html>\r\n");
        bufferedWriter.close();
    }

    public void putResponse() throws IOException {
        bufferedWriter.write("HTTP/1.1 200 OK\r\n");
        bufferedWriter.close();
    }

    public void postResponse() throws IOException {
        bufferedWriter.write("HTTP/1.1 200 OK\r\n");
        bufferedWriter.write("Content-Type: " + httpRequest.getParameters().get("Content-Type") + "\r\n\r\n");
        bufferedWriter.write(httpRequest.getBody() + "\r\n");
        bufferedWriter.close();
//        Content-Type - в каком формате тело запроса/ответа - req/res
//        Accept - для req - в каком формате он ожидает res
    }

    public void deleteResponse() throws IOException {
        bufferedWriter.write("HTTP/1.1 200 OK\r\n");
        bufferedWriter.close();
    }
}
