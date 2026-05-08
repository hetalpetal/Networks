package practical1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {

    public TCPClient() {}

    public static void main(String[] args) throws IOException {

        // IP Addresses will be discussed in detail in lecture 4
        String IPAddressString = "10.216.34.134";
        InetAddress host = InetAddress.getByName(IPAddressString);

        // Port numbers will be discussed in detail in lecture 5
        int port = 80;

        // This is where we create a socket object
        // That creates the TCP connection
        System.out.println("TCPClient connecting to " + host.toString() + ":" + port);
        Socket clientSocket = new Socket(host, port);

        // Like files, we use readers and writers for convenience
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Writer writer = new OutputStreamWriter(clientSocket.getOutputStream());

        // Sending a message to the server at the other end of the socket
        System.out.println("Sending a message to the server");
        writer.write("GET / HTTP/1.1\r\nHost: www.example.com\r\n\r\n");
        writer.flush();
        // To make better use of bandwidth, messages are not sent
        // until the flush method is used

        // We can read everything the server has sent
        String response;
        while ((response = reader.readLine()) != null) {
            System.out.println(response);
            if (response.equals("0")) {
                break;
            }
        }

        // Close down the connection
        clientSocket.close();
    }
}
