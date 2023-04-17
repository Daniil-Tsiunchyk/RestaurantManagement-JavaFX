package com.example.restaurantmanagement;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private final Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String request;
            while ((request = reader.readLine()) != null) {
                System.out.println("Received request: " + request);
                String response = processRequest(request);
                writer.println(response);
            }

            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private String processRequest(String request) {
        // Обработка запроса и формирование ответа для клиента
        return request;
    }
}
