package com.example.tldr;

import com.example.tldr.Model.Account;
import com.example.tldr.Model.Dataset;
import com.example.tldr.Model.ProductDataset;
import com.example.tldr.Model.ShoppingCart;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class SQLServer {

    String url = "jdbc:mysql://localhost:3306/tldrdb";

    public SQLServer() {
    }

    Connection conn;
    Statement stmt;

    /** NB:Configure with your username and password */
    public void openConnection() throws SQLException {
        conn = DriverManager.getConnection(url, "root", "tempPass");
        stmt = conn.createStatement();
    }

    public void closeConnection() throws SQLException {
        stmt.close();
        conn.close();
    }

    /** SQL Go Below **/

    /** Adds dataset to database */
    public void addDataset(String name, String description, double price, int total, int min, int max)
            throws SQLException {
        openConnection();

        LocalDateTime dt = LocalDateTime.now();
        String sql = "INSERT INTO dataset (name, description, price, tot_available, min_quantity, max_quantity, timestamp_ds) VALUES ('"
                + name + "', '" + description + "', " + price + ", " + total + ", " + min + ", " + max + ", '" + dt
                + "');";
        stmt.executeUpdate(sql);

        closeConnection();
    }

    /** search dataset with query */
    public ArrayList<Dataset> searchForDataset(String title)
            throws SQLException {
        openConnection();

        String sql = "SELECT *  FROM dataset " + "WHERE name  = '" + title + "';";
        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<Dataset> datasets = createDatasetList(rs);

        closeConnection();

        return datasets;

    }

    /** Hides dataset from being displayed in the catalogue */
    public void hideDataset(String id) throws SQLException {
        openConnection();

        String sql =    "UPDATE dataset " +
                        "SET hidden = '1' " +
                        "WHERE ID  = '" + id + "';";

        stmt.executeUpdate(sql);

        closeConnection();
    }

    public void approveOrder(String id) throws SQLException {
        openConnection();

        String sql = "UPDATE tldrdb.purchase SET approved = '1' WHERE cartID = '"+id+"';";

        stmt.executeUpdate(sql);

        closeConnection();
    }

    /** Adds account to database */
    public void addAccount(String name, String email, String password, LocalDateTime dt) throws SQLException {
        openConnection();

        String sql = "INSERT INTO account(name, email, password, timestamp_ac) VALUES('" + name + "', '" + email
                + "', '" + password + "', '" + dt + "');";
        stmt.executeUpdate(sql);

        closeConnection();
    }

    /** Authentication */
    public Account signIn(String name, String password) throws SQLException {
        Account acc = null;
        openConnection();

        ResultSet rs = stmt.executeQuery("SELECT * FROM account WHERE name = '" + name + "' AND password = '" + password + "';");

        while (rs.next()) {
            acc = new Account(rs.getString(2), rs.getString(3), rs.getString(4));
            acc.setId(rs.getInt(1));
            //acc.setDt(rs.getTimestamp(5).toLocalDateTime());
        }

        closeConnection();

        return acc;
    }

    /** Get all datasets from database that aren't hidden*/
    public ArrayList<Dataset> getDatasets(int hidden) throws SQLException {

        openConnection();

        String sql = "SELECT * FROM Dataset;";

        if(hidden==1) {
            sql = "SELECT * FROM Dataset WHERE hidden = '0';";
        }

        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<Dataset> datasets = createDatasetList(rs);

        closeConnection();

        return datasets;
    }

    public void editDataset(Dataset dataset) throws SQLException {

        String sql = "UPDATE `tldrdb`.`dataset` " +
                "SET `description` = '"+dataset.getDescription()+"', " +
                "`price` = '"+dataset.getPrice_per_point()+"', " +
                "`tot_available` = '"+dataset.getAvailable_dataPoints()+"', " +
                "`min_quantity` = '"+dataset.getMinQuantity()+"', " +
                "`max_quantity` = '"+dataset.getMaxQuantity()+"' " +
                "WHERE `ID` = '"+dataset.getId()+"';";

        openConnection();
        stmt.executeUpdate(sql);
        closeConnection();
    }

    /** get all previous shopping carts/purchases for account */
    public ArrayList<ShoppingCart> getAllShoppingCarts(String accountID) throws SQLException {

        String sql = "SELECT DISTINCT cartID FROM purchase;";

        if(Integer.parseInt(accountID) > 0) { //account id = 0 is for admin
            sql = "SELECT DISTINCT cartID FROM purchase where accID = '" + accountID + "';";
        }

        openConnection();

        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();

        while(rs.next()){
            shoppingCarts.add(getShoppingCart(rs.getInt(1)));
        }

        closeConnection();

        return shoppingCarts;

    }

    /** Insert purchases into database */
    public void addPurchases(ShoppingCart shoppingCart, int accid) throws SQLException {
        openConnection();

        String sql = "SELECT cartID from purchase order by cartID desc";
        ResultSet rs = stmt.executeQuery(sql);
        int id = 0;
        if (rs.next()) {
            id = rs.getInt(1) + 1;
        }

        LocalDateTime dt = LocalDateTime.now();

        for(ProductDataset purchase:shoppingCart.getCart()) {
            sql = "INSERT INTO purchase(cartID, accID, timestamp_purchase, quantity, datasetID, price) " +
                    "VALUES('" + id + "', '" + accid
                    + "', '" + dt + "', '" + purchase.getQuantity() + "', '"+purchase.getId() +"', '"+purchase.getTotal_price()+"');";
            stmt.executeUpdate(sql);
        }

        closeConnection();
    }

    /** Get a past shopping cart/purchase using shopping cart ID */
    public ShoppingCart getShoppingCart(int shoppingCartID) throws SQLException {

        String sql = "SELECT * from purchase WHERE cartID = '" + shoppingCartID + "';";

        openConnection();
        ResultSet rs = stmt.executeQuery(sql);

        ShoppingCart sc = new ShoppingCart(shoppingCartID);

        while(rs.next()){
            Dataset ds = getDataset(rs.getInt(5));
            ProductDataset pd = new ProductDataset(ds, rs.getInt(4));
            pd.setTotal_price(rs.getInt(6));
            sc.addDataSet(pd);
            sc.setApproved(rs.getInt(7)==1);
        }

        closeConnection();

        return sc;
    }

    public HashMap<String, Integer> getOrderStats() throws SQLException {

        openConnection();

        HashMap<String, Integer> orderResults = new HashMap<>();
        String sql = "SELECT datasetID, count(datasetID) FROM tldrdb.purchase group by datasetID;";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            orderResults.put(getDataset(rs.getInt(1)).getTitle(), rs.getInt(2));
        }

        closeConnection();

        return orderResults;
    }

    /** Get Dataset from database using id */
    public Dataset getDataset(int id) throws SQLException{
        openConnection();

        String sql = "SELECT * from dataset WHERE ID = '" + id + "';";
        ResultSet rs = stmt.executeQuery(sql);

        Dataset ret = createDatasetList(rs).get(0);

        closeConnection();

        return ret;
    }


    /** Auxiliary Methods */

    public ArrayList<Dataset> createDatasetList(ResultSet rs) throws SQLException {

        ArrayList<Dataset> datasets = new ArrayList<>();

        while (rs.next()) {
            Dataset dataset = new Dataset();
            dataset.setId(rs.getInt(1));
            dataset.setTitle(rs.getString(2));
            dataset.setDescription(rs.getString(3));
            dataset.setPrice_per_point(rs.getDouble(4));
            dataset.setAvailable_dataPoints(rs.getInt(5));
            dataset.setMinQuantity(rs.getInt(6));
            dataset.setMaxQuantity(rs.getInt(7));
            // dataset.setTimestamp(rs.getTimestamp(8).toLocalDateTime());
            datasets.add(dataset);
        }

        return datasets;
    }

}
