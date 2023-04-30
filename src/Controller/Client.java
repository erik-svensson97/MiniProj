package Controller;

import Model.Product;
import View.MainForm;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
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
    private MainForm mainForm;
    private ObjectOutputStream oos;

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
        //Outputstream to send objects to the server.
        oos = new ObjectOutputStream(socket.getOutputStream());
    }

    /**
     * Sends a message to the server.
     * @param price The type of the product to send.
     * @param price The price of the product to send.
     * @throws IOException
     */
    //TODO: Create function to send different objects to the server for example, sendProductToServer and sendUserToServer.
    //TODO: Replace the function below as it is only an example for now.
    public void sendMessageToServer(String type, double price) throws IOException {
        Product product = new Product(type, price);
        oos.writeObject(product);
        oos.flush();
    }

    /**
     * Reads a message that was received from the server.
     */
    public void readMessagesFromServer() {
        new Thread(() -> {
            try {
                //Input stream to read objects sent from the server.
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                while (true) {
                    //Read the object.
                    Object object = ois.readObject();
                    //Check the type of the object.
                    if(object instanceof String){
                        String s = (String) object;
                        handleMessagesFromServer(s);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    /**
     * Handles messages from the server.
     * The function decides what to do based on the message.
     * @param message
     */
    public void handleMessagesFromServer(String message){
        System.out.println("Response from server: " + message);
        switch(message){
            //Add code here to do different things based on message.
            //An example would be switching the view.
        }
    }

}
