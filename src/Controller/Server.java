package Controller;

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
                                handleUserLoginFromClient(user);
                            }
                            else {
                                //Register user.
                                handleUserRegisterFromClient(user);
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
        sendStringMessageToClient("The server received the product.");
    }

    /**
     * Handles the user registration.
     * @param user The user object that was sent from the client.
     */
    public void handleUserRegisterFromClient(User user) throws IOException {
        sendStringMessageToClient("The user has been registered.");
        userProcedures.createUser(user.getUsername(), user.getPassword(), user.getDateOfBirth(), user.getEmail());
    }

    /**
     * Handles the user login.
     * @param user The user object that was sent from the client.
     */
    public void handleUserLoginFromClient(User user) throws IOException {
        int userId = userProcedures.signInUser(user.getUsername(), user.getPassword());
        //If higher than 0, login was successfull.
        if(userId > 0){
            sendStringMessageToClient("loginSuccess");
            //Send the user id that was returned from the database to the client.
            sendUserIdToClient(userId);
        }
        else {
            sendStringMessageToClient("loginFailed");
        }
    }

    /**
     * This function sends a String message to the client from the server.
     * @param message
     * @throws IOException
     */
    public void sendStringMessageToClient(String message) throws IOException {
        oos.writeObject(message);
        oos.flush();
    }

    /**
     * Sends the userid that was returned from the database after signing in to the client.
     * @param userId The users id.
     * @throws IOException
     */
    public void sendUserIdToClient(int userId) throws IOException {
        oos.writeObject(userId);
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
