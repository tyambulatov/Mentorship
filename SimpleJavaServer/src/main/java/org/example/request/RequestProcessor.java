package org.example.request;

import org.example.HttpRequest;
import org.example.response.ResponseProcessor;

import java.io.IOException;
import java.net.Socket;

public class RequestProcessor {
    Socket socket;
    HttpRequest httpRequest;
    ResponseProcessor responseProcessor;

    public RequestProcessor(Socket socket, HttpRequest httpRequest) throws IOException {
        this.socket = socket;
        this.httpRequest = httpRequest;
        responseProcessor = new ResponseProcessor(socket, httpRequest);
    }

    public void process() throws IOException {
        switch (httpRequest.getMethod()) {
            case GET -> getRequest();
            case PUT -> putRequest();
            case POST -> postRequest();
            case DELETE -> deleteRequest();
        }
    }

    private void getRequest() throws IOException {
        checkBodyIsEmpty();
        responseProcessor.getResponse();
    }

    private void putRequest() throws IOException {
        checkBodyIsNotEmpty();
        responseProcessor.putResponse();
    }


    private void postRequest() throws IOException {
        checkBodyIsNotEmpty();
        responseProcessor.postResponse();
    }

    private void deleteRequest() throws IOException {
        checkBodyIsEmpty();
        responseProcessor.deleteResponse();
    }


    private void checkBodyIsEmpty() {
        if (!httpRequest.getBody().isEmpty()) throw new IllegalArgumentException("Invalid request: body is not empty");
    }

    private void checkBodyIsNotEmpty() {
        if (httpRequest.getBody().isEmpty()) throw new IllegalArgumentException("Invalid request: body is empty");
    }

}
