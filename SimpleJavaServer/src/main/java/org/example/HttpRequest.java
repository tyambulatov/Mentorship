package org.example;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    public enum Method {
        GET,
        PUT,
        POST,
        DELETE
    }

    private Method method;
    private String path;
    private final Map<String, String> parameters = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();
    private String body = "";

    public HttpRequest(String httpRequest) {
        checkRequestNotEmpty(httpRequest);
        parse(httpRequest);
    }

    private void parse(String httpRequest) {
        String[] requestParts = httpRequest.split("\r\n\r\n", 2);
        String[] requestLines = requestParts[0].split("\r\n");

        requestLineParsing(requestLines[0]);
        requestHeadersParse(requestLines);

        if (requestParts.length > 1) {
            body = requestParts[1];
        }
    }

    private void requestHeadersParse(String[] requestLines) {
        for (int i = 1; i < requestLines.length; i++) {
            String[] headerParts = requestLines[i].split(": ");
            headers.put(headerParts[0], headerParts[1]);
        }
    }

    private void requestLineParsing(String requestLine) {
        String[] requestLineParts = requestLine.split(" ");
        method = Method.valueOf(requestLineParts[0]);

        String[] strings = requestLineParts[1].split("\\?");
        path = strings[0];

        if (strings.length > 1) {
            String[] parametersEqualsValue = strings[1].split("&");

            for (String s : parametersEqualsValue) {
                String[] parameterValue = s.split("=");
                parameters.put(parameterValue[0], parameterValue[1]);
            }
        }
    }

    private void checkRequestNotEmpty(String httpRequest) {
        if (httpRequest.isEmpty()) throw new IllegalArgumentException("Empty request");
    }

    public Method getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "HttpRequest{\r\n" +
                "method=" + method +
                ",\r\n path='" + path + '\'' +
                ",\r\n parameters=" + parameters +
                ",\r\n headers=" + headers +
                ",\r\n body='" + body + '\'' +
                "}\r\n";
    }
}
