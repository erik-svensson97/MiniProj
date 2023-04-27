package View;

import Controller.Client;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;

/**
 * This class is the main JFrame of the application.
 * @Author Mahmoud Daabas
 */
public class MainForm {

    /**
     * Declare variables.
     */
    private JFrame mainFrame;
    private Client c;

    /**
     * Constructor
     */
    public MainForm(Client c){
        //Assign variables.
        this.c = c;
        mainFrame = new JFrame("Marketplace Application");
        setLookAndFeel();
        setLoginPanel();
        mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setSize(950, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
    }

    /**
     * Sets the login panel when the application starts.
     */
    public void setLoginPanel(){
        //Create an object of the LoginForm and send the previously created controller to it.
        LoginForm lf = new LoginForm(c);
        mainFrame.getContentPane().add(lf.getLoginPanel());
    }

    /**
     * Sets the look and feel for the application.
     */
    public void setLookAndFeel(){
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }
}
