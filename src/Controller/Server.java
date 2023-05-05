package Controller;

import DataAccessLayer.DatabaseConnection;
import DataAccessLayer.UserProcedures;
import Model.Product;
import Model.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This is the server class.
 */
public class Server {

    /**
     * Declare variables.
     */
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream oos;
    private UserProcedures userProcedures;

    /**
     * This function starts the server when it's called.
     * @throws IOException
     */
    public void startServer() throws IOException {
        serverSocket = new ServerSocket(8080);
        System.out.println("Server started on port 8080");
        userProcedures = new UserProcedures();

        while (true) {
            //Accept a clients connection.
            clientSocket = serverSocket.accept();
            //Create output stream to send objects to the client.
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            System.out.println("Client connected: " + clientSocket.getInetAddress());
            new Thread(() -> {
                try {
                    //Create an input stream to read objects sent from the client.
                    ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                    while(true){
                        //Read the object.
                        Object object = ois.readObject();
                        //Check the type of the object.
                        if(object instanceof Product){
                            //Add product to database here.
                            Product product = (Product) object;
                            handleProductFromClient(product);
                        }
                        else if(object instanceof User){
                            //Log in or register user here.
                            User user = (User) object;
                            //Check if user is registered.
                            if(user.isRegistered()){
                                //Login user.
                                handleUserLoginFromServer(user);
                            }
                            else {
                                //Register user.
                                handleUserRegisterFromServer(user);
                            }
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
    public void handleProductFromClient(Product product) throws IOException {
        System.out.println("Received Product object from client: " + /*product.getType()*/  " " + product.getPrice());
        //Do something with the product.
        sendMessageToClient("The server received the product.");
    }

    /**
     * Handles the user registration.
     * @param user The user object that was sent from the client.
     */
    public void handleUserRegisterFromServer(User user) throws IOException {
        sendMessageToClient("The user has been registered.");
        userProcedures.createUser(user.getUsername(), user.getPassword(), user.getDateOfBirth(), user.getEmail());
    }

    /**
     * Handles the user login.
     * @param user The user object that was sent from the client.
     */
    public void handleUserLoginFromServer(User user) throws IOException {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println("Signing in the user with the data above.");
        sendMessageToClient("The user has been signed in.");
    }

    /**
     * This function sends a String message to the client from the server.
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
