package Model;

import Controller.Server;

import java.io.Serializable;

public class Request implements Serializable {
    private int buyer_id;
    private int product_id;

    public Request(int buyer_id, int product_id) {
        this.buyer_id = buyer_id;
        this.product_id = product_id;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
