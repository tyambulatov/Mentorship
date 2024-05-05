package org.example.request;

import org.example.HttpRequest;

public interface RequestRule {
    boolean matches(HttpRequest request);
}
