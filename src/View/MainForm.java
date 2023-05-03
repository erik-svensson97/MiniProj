package View;

import Controller.Client;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;

/**
 * This class is the main JFrame of the application.
 */
public class MainForm {

    /**
     * Declare variables.
     */
    private JFrame mainFrame;
    private Client c;
    private LoginForm login;
    private RegisterForm register;

    /**
     * Constructor
     */
    public MainForm(Client c){
        //Assign client variable.
        this.c = c;

        setLookAndFeel();
        mainFrame = new JFrame("Marketplace Application");
        mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(950, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);

        //Create the applications forms and pass the client object to them.
        login = new LoginForm(c);
        register = new RegisterForm(c);


        //Set the login panel.
        setLoginPanel();

        //Make the main frame visible.
        mainFrame.setVisible(true);
    }

    /**
     * Sets the login panel when the application starts for the first time or when it's called.
     */
    public void setLoginPanel(){
        mainFrame.setContentPane(login.getLoginPanel());
        mainFrame.getContentPane().revalidate();
    }

    /**
     * Sets the register panel when called.
     */
    public void setRegisterPanel(){
        mainFrame.setContentPane(register.getRegisterPanel());
        mainFrame.getContentPane().revalidate();
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
