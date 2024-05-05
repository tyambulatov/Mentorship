package org.example.request;

import java.io.IOException;

import org.example.HttpRequest;

public interface ReqProcessor {
    void process(HttpRequest httpRequest) throws IOException;
}
