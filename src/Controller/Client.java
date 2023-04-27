package Controller;

import View.MainForm;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This is the class for the client.
 * It will connect to the server and start the GUI for the user.
 * @Author Mahmoud Daabas
 */
public class Client {
    /**
     * Declare variables
     */
    private Socket socket;
    private PrintWriter out;
    private InputStream in;
    private MainForm mainForm;

    /**
     * Constructor
     */
    public Client(){
        try {
            //Connect to the server.
            connectToServer();
            //Start thread to read messages from the server.
            readMessagesFromServer();
            //Start main form and send a client object to it.
            mainForm = new MainForm(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Connects to the server.
     * @throws IOException
     */
    public void connectToServer() throws IOException {
        socket = new Socket("localhost", 8080);
    }

    /**
     * Sends a message to the server.
     * @param message The message to send.
     * @throws IOException
     */
    public void sendMessageToServer(String message) throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println(message);
    }

    /**
     * Reads a message that was recieved from the server?
     */
    public void readMessagesFromServer() {
        new Thread(() -> {
            try {
                InputStream in = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while (true) {
                    String response = reader.readLine();
                    if (response == null) {
                        // If the response is null, the server has closed the connection.
                        System.out.println("Connection to server closed.");
                        break;
                    }
                    System.out.println("Response from server: " + response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
