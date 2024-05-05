package org.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class UtilMethods {
    public static String parseHttpRequestString(Socket socket) throws IOException {
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
