package Model;

import java.io.Serializable;

/**
 * This class represents a product.
 * @Author Mahmoud Daabas
 */
public class Product implements Serializable {

    /**
     * Declare variables.
     */
    private String type;
    private double price;

    /**
     * Constructor.
     * @param type The type of the product.
     * @param price The price of the product.
     */
    public Product(String type, double price){
        this.type = type;
        this.price = price;
    }

    /**
     * Gets the type of the product.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the price of the product.
     * @return price.
     */
    public double getPrice() {
        return price;
    }
}
