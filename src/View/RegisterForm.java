package View;

import Controller.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This class creates the register frame of the application.
 */
public class RegisterForm implements ActionListener {

    private JPanel registerPanel;
    private Client c;
    private JTextField passwordTextField;
    private JTextField usernameTextField;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JButton registerButton;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JLabel dateOfBirthLabel;
    private JTextField dateOfBirthTextField;
    private JButton goBackButton;

    public RegisterForm(Client c) {
        //Assign the client variable to the local one.
        this.c = c;

        //construct components
        passwordTextField = new JTextField (5);
        usernameTextField = new JTextField (5);
        userNameLabel = new JLabel ("Username:");
        passwordLabel = new JLabel ("Password:");
        registerButton = new JButton ("Register");
        emailLabel = new JLabel ("Email:");
        emailTextField = new JTextField (5);
        dateOfBirthLabel = new JLabel ("Date of Birth:");
        dateOfBirthTextField = new JTextField (5);
        goBackButton = new JButton("Go Back");

        //adjust size and set layout
        registerPanel = new JPanel();
        registerPanel.setPreferredSize (new Dimension(944, 569));
        registerPanel.setLayout (null);

        //add components
        registerPanel.add (passwordTextField);
        registerPanel. add (usernameTextField);
        registerPanel.add (userNameLabel);
        registerPanel.add (passwordLabel);
        registerPanel.add (registerButton);
        registerPanel.add (emailLabel);
        registerPanel.add (emailTextField);
        registerPanel.add (dateOfBirthLabel);
        registerPanel.add (dateOfBirthTextField);
        registerPanel.add (goBackButton);

        //set component bounds (only needed by Absolute Positioning)
        passwordTextField.setBounds (385, 205, 165, 25);
        usernameTextField.setBounds (385, 145, 165, 25);
        userNameLabel.setBounds (385, 115, 100, 25);
        passwordLabel.setBounds (385, 175, 100, 25);
        registerButton.setBounds (410, 380, 120, 25);
        emailLabel.setBounds (385, 235, 100, 25);
        emailTextField.setBounds (385, 265, 165, 25);
        dateOfBirthLabel.setBounds (385, 295, 100, 25);
        dateOfBirthTextField.setBounds (385, 325, 165, 25);
        goBackButton.setBounds (410, 420, 120, 25);

        //Add listeners for the buttons.
        addListeners();
    }

    /**
     * Adds a ActionListener and ActionCommands to the buttons.
     */
    public void addListeners(){
        registerButton.addActionListener(this);
        registerButton.setActionCommand("register");
        goBackButton.addActionListener(this);
        goBackButton.setActionCommand("goBack");
    }

    /**
     * Returns the register panel.
     * @return registerPanel
     */
    public JPanel getRegisterPanel(){
        return this.registerPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        try {
            switch(action){
                case "register":
                    //Register user to database and switch view to login again.
                    c.sendUserToServerRegister(usernameTextField.getText(), passwordTextField.getText(), dateOfBirthTextField.getText(), emailTextField.getText(), false);
                    c.getMainForm().setLoginPanel();
                    break;
                case "goBack":
                    //Go back to the login panel.
                    c.getMainForm().setLoginPanel();
                    break;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
