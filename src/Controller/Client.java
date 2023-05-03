package Controller;

import Model.Product;
import Model.User;
import View.MainForm;

import java.io.*;
import java.net.Socket;

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
     * Sends a product object to the server.
     * @param type The type of the product.
     * @param price The price of the product.
     */
    public void sendProductToServer(String type, double price) throws IOException {
        Product product = new Product(type, price);
        oos.writeObject(product);
        oos.flush();
    }

    /**
     * Sends a user object to the server for registering.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param dateOfBirth The date of birth of the user.
     * @param email The email of the user.
     * @throws IOException
     */
    public void sendUserToServerRegister(String username, String password, String dateOfBirth, String email, boolean registered) throws IOException {
        User user = new User(username, password, dateOfBirth, email, registered);
        oos.writeObject(user);
        oos.flush();
    }

    /**
     * Sends a user object to the server for logging in.
     * @param username The username of the user.
     * @param password The password of the user.
     * @throws IOException
     */
    public void sendUserToServerLogin(String username, String password) throws IOException {
        User user = new User(username, password);
        oos.writeObject(user);
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
                        handleStringMessagesFromServer(s);
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
    public void handleStringMessagesFromServer(String message){
        System.out.println("Response from server: " + message);
        switch(message){
            //Add code here to do different things based on message.
            //An example would be switching the view.
        }
    }

}
