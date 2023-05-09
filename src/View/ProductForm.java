package View;


import Controller.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ProductForm implements ActionListener {
    private JPanel productPanel;
    private JButton addProductButton;
    private JButton placeHolderButton;
    private JTable table;
    private Client c;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

    public ProductForm(Client c) {
        //Apply passed client variable to the local one.
        this.c = c;

        //construct components
        addProductButton = new JButton("Add Product");
        placeHolderButton = new JButton ("Placeholder");


        //adjust size and set layout
        productPanel = new JPanel();
        productPanel.setPreferredSize (new Dimension(944, 569));
        productPanel.setLayout (null);

        //add components
        productPanel.add (addProductButton);
        productPanel.add (placeHolderButton);

        //set component bounds (only needed by Absolute Positioning)
        addProductButton.setBounds (185, 450, 120, 25);
        placeHolderButton.setBounds (590, 450, 120, 25);

        //Add listeners to buttons
        addListeners();
    }

    /**
     * Creates the JTable that is displayed.
     * @param tableModel A table model with data from the database.
     */
    public void createTableModel(DefaultTableModel tableModel){
        //If the tablemodel in the class is null, create the JTable for the first time.
        //If it is not null, it means that the table is already created and just needs to be updated with new data.
        if(this.tableModel == null){
            //Set the tableModel passed in the param to the local one, so it can be used outside of this function.
            this.tableModel = tableModel;
            table = new JTable(this.tableModel);
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(80, 60, 785, 360);
            productPanel.add(scrollPane);
            productPanel.revalidate();
        }
        else {
            //Just update the table if it's not null.
            this.tableModel = tableModel;
            //Set the new updated model to the jtable.
            table.setModel(this.tableModel);
        }
    }

    /**
     * Adds ActionListeners for the buttons.
     */
    public void addListeners(){
        addProductButton.addActionListener(this);
        addProductButton.setActionCommand("addProduct");
    }

    /**
     * Adds a product for sale to the database and the GUI.
     */
    public void addProduct(){
        try {
            String type=JOptionPane.showInputDialog(null,"Enter the products type");
            int price=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the products price"));
            String productionYear=JOptionPane.showInputDialog(null,"Enter the products production year");
            String color=JOptionPane.showInputDialog(null,"Enter the products color");
            String condition=JOptionPane.showInputDialog(null,"Enter the products condition");
            c.sendProductToServer(type, price, productionYear, color, condition);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the product panel.
     * @return productPanel
     */
    public JPanel getProductPanel(){
        return this.productPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch(action){
            case "addProduct":
                addProduct();
                break;
        }

    }
}
