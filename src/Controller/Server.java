package Controller;

import Model.Product;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
    private ObjectOutputStream oos;

    /**
     * This function starts the server when it's called.
     * @throws IOException
     */
    public void startServer() throws IOException {
        serverSocket = new ServerSocket(8080);
        System.out.println("Server started on port 8080");

        while (true) {
            clientSocket = serverSocket.accept();
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            System.out.println("Client connected: " + clientSocket.getInetAddress());
            new Thread(() -> {
                try {
                    ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                    while(true){
                        Object object = ois.readObject();
                        if(object instanceof Product){
                            Product p = (Product) object;
                            handleMessageFromClient(p);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    /**
     * Handles messages from the client.
     * The function decides what to do based on the message.
     */
    public void handleMessageFromClient(Product p) throws IOException {
        System.out.println("Received Product object from client: " + p.getType() + " " + p.getPrice());
        //Do something with the product.
        sendMessageToClient("The server received the product.");
    }

    /**
     * This function sends a message to the client from the server.
     * @param message
     * @throws IOException
     */
    public void sendMessageToClient(String message) throws IOException {
        oos.writeObject(message);
        oos.flush();
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
