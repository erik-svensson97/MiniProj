package View;

import Controller.Client;
import Model.TableType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ProfileForm implements ActionListener {
    private JPanel profilePanel;
    private JButton returnButton;
    private Client c;
    private JScrollPane activePosts;
    private JScrollPane buyRequests;
    private DefaultTableModel activePostTM;
    private DefaultTableModel buyRequestsTM;
    private JTable activePostTable;
    private JTable buyRequestsTable;
    private JPanel activePostPanel;
    private JPanel buyRequestsPanel;


    public ProfileForm(Client c) {
        this.c = c;
        returnButton = new JButton("Marketplace");

        profilePanel = new JPanel();
        profilePanel.setPreferredSize (new Dimension(944, 569));
        profilePanel.setLayout (null);

        profilePanel.add (returnButton);

        returnButton.setBounds (100, 450, 120, 25);

        addListeners();
    }

    public void createTableModels(DefaultTableModel defaultTableModel, TableType tableType){
        DefaultTableModel tableModel;
        JTable table;
        JScrollPane scrollPane;
        int xcoordinates;

        if(tableType == TableType.activePosts){
            tableModel = activePostTM;
            table = activePostTable;
            scrollPane = activePosts;
            xcoordinates = 50;

        } else {
            tableModel = buyRequestsTM;
            table = buyRequestsTable;
            scrollPane = buyRequests;
            xcoordinates = 150;
        }
        if(tableModel == null){
            //Set the tableModel passed in the param to the local one, so it can be used outside of this function.
            tableModel = defaultTableModel;
            table = new JTable(tableModel);
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(xcoordinates, 60, 350, 360);
            profilePanel.add(scrollPane);
            profilePanel.revalidate();
        }
        else {
            //Just update the table if it's not null.
            tableModel = defaultTableModel;
            //Set the new updated model to the jtable.
            table.setModel(tableModel);
        }
    }

    private void addListeners() {
        returnButton.addActionListener(this);
        returnButton.setActionCommand("marketplace");
    }

    private void returnToMarket() throws IOException {
        c.accessMarketplace();
    }

    public JPanel getProfilePanel() {
        return profilePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action){
            case "marketplace":
                try {
                    returnToMarket();
                    c.getMainForm().setProductPanel();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }
}
