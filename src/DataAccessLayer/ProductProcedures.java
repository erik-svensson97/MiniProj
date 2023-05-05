package DataAccessLayer;

import Model.Product;
import Model.User;

import java.sql.Date;
import java.util.List;

public class ProductProcedures {
    //3. Hämtar listan från databas och skickar tbx till Server som sen skickar till client.

    public boolean purchaseProd(int product_id, String product_name, int buyer_id){ //Buyer ska hämta om sina purchased products
        // tar bort bort product från listan
        //Lägger till i order_history på köparens id, skriver produktens namn i product_name &
        // dagens datum i date_of_transaction
        return false;
    }

    public boolean buyReq(int user_id, int product_id){ // Måste uppdatera ägaren av produkten att en req finns
        //En rad skapas i request med user_id och product_id
        return false;
    }

    public boolean register(User user){
        return false;
    }

    public boolean login(){
        return false;
    }

    public List<Object> getBuyReqs(int user_id){
        //The user gets all of the ids of its products
        //and checks if there are any requests on the product_id
        return null; // returns a list of username of user & product_id from product
    }

    public boolean registerNewProd(Product product){ //Skicka ut till alla online users att products är uppdaterad
        //Ny rad i products
        return false;
    }

    public List<Object> getPurchasedProducts(Date earliestDate){ //Från detta datumet och framåt
        //Hämtar listan med alla produkter som man köpt från och med earliestDate
        // om earliestDate är null så hämtas alla
        return null;
    }

}
