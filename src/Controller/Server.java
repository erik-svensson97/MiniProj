package Controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This is the server class.
 * @Author Mahmoud Daabas
 */
public class Server {

    /**
     * Declare variables.
     */
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;

    /**
     * This function starts the server when its called.
     * @throws IOException
     */
    public void startServer() throws IOException {
        serverSocket = new ServerSocket(8080);
        System.out.println("Server started on port 8080");

        while (true) {
            clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        //Redirect messages from the client to this function.
                        readMessageFromClient(inputLine);
                    }
                    //in.close();
                    //clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * This function reads the messages from the client.
     * After reading a message, it decides what to do.
     */
    public void readMessageFromClient(String message) throws IOException {
        System.out.println("Message from the client is: " + message);
        sendMessageToClient("The server recieved the message.");
        switch(message){
            //Add cases here to do different things based on the message.
        }
    }

    /**
     * This function sends a message to the client from the server.
     * @param message
     * @throws IOException
     */
    public void sendMessageToClient(String message) throws IOException {
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println(message);
    }

    /**
     * Main function to start the server.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Server s = new Server();
        s.startServer();
    }
}
