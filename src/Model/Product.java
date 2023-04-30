package Model;

import java.io.Serializable;

public class Product implements Serializable {
    private String type;
    private double price;

    public Product(String type, double price){
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}
