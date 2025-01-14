package Controller;

import DataAccessLayer.ProductProcedures;
import DataAccessLayer.UserProcedures;
import Model.Product;
import Model.Request;
import Model.User;

import javax.swing.table.DefaultTableModel;
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
    private ProductProcedures productProcedures;

    /**
     * This function starts the server when it's called.
     * @throws IOException
     */
    public void startServer() throws IOException {
        serverSocket = new ServerSocket(8080);
        System.out.println("Server started on port 8080");
        userProcedures = new UserProcedures();
        productProcedures = new ProductProcedures();

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
                        else if (object instanceof Request){
                            Request request = (Request) object;
                            handleBuyReqFromClient(request);
                        }
                        else if (object instanceof String){
                            if (object == "marketplace") {
                                getAllProductsFromDatabase();
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
     * Handles buy requests from the client
     * @param request
     */
    private boolean handleBuyReqFromClient(Request request) {
        return productProcedures.buyReq(request.getBuyer_id(), request.getProduct_id());
    }

    /**
     * Handles messages from the client.
     * The function decides what to do based on the message.
     */
    public void handleProductFromClient(Product product) throws IOException {
        productProcedures.registerProdForSale(product);
        //Get all the products from the database to update the GUI.
        getAllProductsFromDatabase();
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
            //Get all the products from the database and send to the client to show on the GUI.
            getAllProductsFromDatabase();
        }
        else {
            sendStringMessageToClient("loginFailed");
        }
    }

    /**
     * This function gets all the products from the database.
     * @throws IOException
     */
    public void getAllProductsFromDatabase() throws IOException {
        DefaultTableModel tableModel = productProcedures.getAllProducts();
        //Send the DefaultTableModel holding the data to the client.
        sendDefaultTableModelToClient(tableModel);
    }

    /**
     * This function sends a DefaultTableModel to the client from the server.
     * @param tableModel The table model with the data that will be displayed in a JTable.
     * @throws IOException
     */
    public void sendDefaultTableModelToClient(DefaultTableModel tableModel) throws IOException {
        oos.writeObject(tableModel);
        oos.flush();
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
