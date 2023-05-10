package DataAccessLayer;

import Model.Product;
import Model.User;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.List;

public class ProductProcedures {

    public static void main(String[] args) {
        ProductProcedures pc = new ProductProcedures();
        pc.getAllProducts();
    }

    /**
     * Get all the products that are available for sale from the database.
     * @return Products for sale
     */
    public DefaultTableModel getAllProducts() {
        DatabaseConnection dc = new DatabaseConnection();
        try (CallableStatement statement = dc.getConnection().prepareCall("{ call get_all_products() }")) {
            //Create the table model
            DefaultTableModel tableModel = new DefaultTableModel();
            String[] columnNames = {"ProductID", "SellerID", "Type", "Price", "Production Year", "Color", "Condition"};
            //Add the column names to the table model
            for (int i = 0; i < columnNames.length; i++) {
                tableModel.addColumn(columnNames[i]);
            }
            int counter = 0;

            statement.executeQuery();
            ResultSet res = statement.getResultSet();
            while (res.next()) {
                //Add the data to the table model
                tableModel.insertRow(counter, new Object[]{res.getString(1), res.getString(2), res.getString(3),
                res.getString(4), res.getString(5), res.getString(6), res.getString(7)});
                counter++;
            }
            //Return the table model with the data
            return tableModel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * Adds a product for sale.
     * @param product The product to add for sale.
     */
    public int registerProdForSale(Product product){
        DatabaseConnection dc = new DatabaseConnection();
        try( CallableStatement statement = dc.getConnection().prepareCall("{ ? = call add_product(?, ?, ?, ?, ?, ?) }")) {
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setInt(2, product.getUser_id());
            statement.setString(3, product.getTitle());
            statement.setInt(4, (int) product.getPrice());
            statement.setDate(5, Date.valueOf(product.getYear_of_production()));
            statement.setString(6, product.getColor());
            statement.setString(7, product.getCondition());
            statement.execute();
            int result = statement.getInt(1);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean purchaseProd(int product_id, String product_name, int buyer_id){ //Buyer ska hämta om sina purchased products

        // tar bort bort product från listan
        //Lägger till i order_history på köparens id, skriver produktens namn i product_name &
        // dagens datum i date_of_transaction
        return false;
    }

    public boolean buyReq(int user_id, int product_id){ // Måste uppdatera ägaren av produkten att en req finns
        DatabaseConnection dc = new DatabaseConnection();
        try (CallableStatement statement = dc.getConnection().prepareCall("{ ? = call buyreq(?,?) }")) {
            statement.registerOutParameter(1, Types.BOOLEAN);
            statement.setInt(2, user_id);
            statement.setInt(3, product_id);
            statement.execute();
            boolean result = statement.getBoolean(1);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
