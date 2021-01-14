package Website.Functions;

import java.sql.*;
/*This class is used to access the details of a product from the database given its ID */
public class AccessDetails {
String brand;
String name;
String saleLimit;
float unitPrice;
String amount;
    public AccessDetails(String id){
        int intId = Integer.parseInt(id);
        System.out.println(intId);
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try {
            Class.forName("org.postgresql.Driver");
            //Making connection to our database
            Connection db = DriverManager.getConnection(dbUrl);
            Statement stmt = db.createStatement();
            //Selecting the row from our shop_product table which contains the product information
            String sqlStr = "SELECT * FROM shop_product WHERE id = " + intId;
            System.out.println(sqlStr);
            ResultSet rs = stmt.executeQuery(sqlStr);
            //extracting values from database
            if (rs.next()) {
                this.unitPrice = rs.getFloat("sell_price");
                this.brand = rs.getString("brand");
                this.name = rs.getString("name");
                this.amount = rs.getString("amount");
                int var = rs.getInt("limit_of_one");
                if (var == 1) {
                    this.saleLimit = "yes";
                }
                else {
                    this.saleLimit = "no";
                }
            }
            rs.close();
            stmt.close();
            db.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public String getBrand() {
        return brand;
    }
    public String getName() {
        return name;
    }
    public String getSaleLimit() {
        return saleLimit;
    }
    public float getUnitPrice() {
        return unitPrice;
    }
    public String getAmount() {
        return amount;
    }
}


