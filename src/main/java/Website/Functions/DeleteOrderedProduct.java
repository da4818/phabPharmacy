
package Website.Functions;
import java.sql.*;
//This class is used when an order is completed by someone at the pharmacy to remove it from the orders database
public class DeleteOrderedProduct {
    public DeleteOrderedProduct (String barcode, String customerID){
        int intId = Integer.parseInt(barcode);
        int custId = Integer.parseInt(customerID);
        System.out.println(intId);
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try {
            Class.forName("org.postgresql.Driver");
            Connection db = DriverManager.getConnection(dbUrl);
            //Makes connection to our database
            Statement stmt = db.createStatement();
            String sqlStr = "DELETE FROM ordered_product WHERE barcode = " + intId + " AND customer_id = " + custId + " AND confirmed_order = TRUE";
            System.out.println(sqlStr);
            //delete rows of orders which have been completed
            ResultSet rs = stmt.executeQuery(sqlStr);
            if (rs.next()) {
                rs.deleteRow();
            }
            rs.close();
            stmt.close();
            db.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}