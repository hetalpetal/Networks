package practical1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public TCPServer() {}

    public static void main(String[] args) throws IOException {

        // Port numbers will be discussed in detail in lecture 5
        int port = 80;

        // The server side is slightly more complex
        // First we have to create a ServerSocket
        System.out.println("Opening the server socket on port " + port);
        ServerSocket serverSocket = new ServerSocket(port);

        // The ServerSocket listens and then creates a Socket object
        // for an incoming connection.
        System.out.println("Server waiting for client...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected!");

        // Read the HTTP request from the client
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Writer writer = new OutputStreamWriter(clientSocket.getOutputStream());

        System.out.println("The client sent:");
        String message;
        while ((message = reader.readLine()) != null && !message.isEmpty()) {
            System.out.println(message);
        }

        System.out.println("Sending a response to the client");
        writer.write("HTTP/1.1 200 OK\r\n");
        writer.write("Content-Type: text/plain\r\n");
        writer.write("Connection: close\r\n");
        writer.write("\r\n");
        writer.write("Hello from server\n");
        writer.flush();

        // Close down the connection
        clientSocket.close();
        serverSocket.close();
    }
}
