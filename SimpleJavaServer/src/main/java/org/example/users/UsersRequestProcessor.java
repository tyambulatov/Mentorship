package org.example.users;

import java.io.IOException;

import org.example.HttpRequest;
import org.example.request.ReqProcessor;

public class UsersRequestProcessor implements ReqProcessor {

    @Override
    public void process(HttpRequest httpRequest) throws IOException {
//        httpRequest -> по нему можно вызывать UserRepo/UserService
    }

}
