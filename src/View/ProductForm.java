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
    private DefaultTableModel model;
    private Client c;

    public ProductForm(Client c) {
        //Apply passed client variable to the local one.
        this.c = c;
        //create the jtable
        createTableModel();


        //construct components
        addProductButton = new JButton("Add Product");
        placeHolderButton = new JButton ("Placeholder");
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);


        //adjust size and set layout
        productPanel = new JPanel();
        productPanel.setPreferredSize (new Dimension(944, 569));
        productPanel.setLayout (null);

        //add components
        productPanel.add (addProductButton);
        productPanel.add (placeHolderButton);
        productPanel.add(scrollPane);

        //set component bounds (only needed by Absolute Positioning)
        addProductButton.setBounds (185, 450, 120, 25);
        placeHolderButton.setBounds (590, 450, 120, 25);
        scrollPane.setBounds (80, 60, 785, 360);
    }

    /**
     * Creates the JTable that is displayed.
     */
    public void createTableModel(){
        String[] columnNames = {"Product", "Price", "Seller"};
        model = new DefaultTableModel();
        for (int i = 0; i < columnNames.length; i++) {
            model.addColumn(columnNames[i]);
        }
        model.insertRow(0, new Object[] {"Macbook", "5000kr", "Random Seller"});
        model.insertRow(1, new Object[] {"Iphone", "12000kr", "Patrick"});
    }

    /**
     * Returns the product panel.
     * @return productPanel
     */
    public JPanel getProductPanel(){
        return this.productPanel;
    }


    /**
     * For testing only.
     */
    /*public static void main (String[] args) {
        ProductForm pf = new ProductForm();
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (pf.getProductPanel());
        frame.pack();
        frame.setVisible (true);
    }*/
}
