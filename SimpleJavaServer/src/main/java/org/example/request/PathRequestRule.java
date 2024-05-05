package org.example.request;

import org.example.HttpRequest;

public class PathRequestRule implements RequestRule {

    private final String path;

    public PathRequestRule(String path) {
        this.path = path;
    }

    @Override
    public boolean matches(HttpRequest request) {
        return request.getPath().startsWith(path);
    }
}
