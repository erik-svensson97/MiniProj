package View;


import Controller.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProductForm {
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
    }

    /**
     * Creates the JTable that is displayed.
     */
    public void createTableModel(DefaultTableModel tableModel){
        //Set the tableModel passed in the param to the local one, so it can be used outside of this function.
        this.tableModel = tableModel;
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(80, 60, 785, 360);
        productPanel.add(scrollPane);
        productPanel.revalidate();
    }

    /**
     * Returns the product panel.
     * @return productPanel
     */
    public JPanel getProductPanel(){
        return this.productPanel;
    }
}
