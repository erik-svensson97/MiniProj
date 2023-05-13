package View;

import Controller.Client;
import Controller.Messager;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChatWindow extends JFrame {
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private Client userClient;
    private Messager messager;

    public ChatWindow(Client client /* Messager messager*/){
        super ("Chat");
        this.userClient = client;
      //  this.messager = messager;
        init();
    }
    public void init(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        chatArea = new JTextArea(20, 50);
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        messageField = new JTextField(40);
        inputPanel.add(messageField, BorderLayout.CENTER);

        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        inputPanel.add(sendButton, BorderLayout.EAST);

        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void display(){
        setVisible(true);
    }
    public String addMessage(){
        return "todo";
    }
    public void updateMessages(ArrayList<String> messages) {
        chatArea.setText("");
        for (String message : messages) {
            chatArea.append(message + "\n");
        }
    }
    private void sendMessage() {
        //currently no functionality for offline messaging, implement later
        String message = messageField.getText().trim();
        if (!message.isEmpty()) {
            User currentUser = userClient.getCurrentUser();
            messager.sendMessage
                    (currentUser.getUsername(),
                    "get receiver from window later",
                    message);
            messageField.setText("");
        }
    }
}
