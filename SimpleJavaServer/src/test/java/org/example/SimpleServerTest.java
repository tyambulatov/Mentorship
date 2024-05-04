package org.example;

import org.example.simpleClient.SimpleClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SimpleServerTest {
    int port = 8080;
    SimpleServer simpleServer;
    SimpleClient simpleClient;
    String request = "GET / HTTP/1.1\r\nHost: localhost:8080\r\nAccept: */*\r\n\r\n";

    @Test
    void test() throws IOException {
        simpleServer = new SimpleServer(port);
        simpleClient = new SimpleClient("localhost", 8080, request);
        new Thread(simpleClient).start();
        new Thread(simpleServer).start();
    }

    @AfterEach
    void tearDown() {
    }
}