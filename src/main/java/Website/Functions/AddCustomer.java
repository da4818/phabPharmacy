package Website.Functions;

import java.sql.*;

import Website.Entities.CreditCard;
import Website.Entities.Customer;


public class AddCustomer {

    public AddCustomer(Customer cust, CreditCard cc) {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try {

            Class.forName("org.postgresql.Driver");
            Connection db = DriverManager.getConnection(dbUrl);
            //Statement stmt = db.createStatement();
            Statement stmt1 = db.createStatement();
            //Statement stmt2 = db.createStatement();
            PreparedStatement pStmt = db.prepareStatement("INSERT INTO customer (first_name,last_name,email,postcode,address,phone_no) VALUES (?,?,?,?,?,?);");
            pStmt.setString(1,cust.first_name);
            pStmt.setString(2,cust.last_name);
            pStmt.setString(3,cust.email);
            pStmt.setString(4,cust.postcode);
            pStmt.setString(5,cust.address);
            pStmt.setString(6,cust.phone_number);
            pStmt.executeUpdate();
            String sqlStr = "SELECT * FROM customer WHERE first_name = cust.first_name() AND email = cust.email;";
            ResultSet rs = stmt1.executeQuery(sqlStr);
            int id = Integer.parseInt(null);
            if (rs.next()) {
                id = rs.getInt("id");
            }

            PreparedStatement pStmt2 = db.prepareStatement("INSERT INTO card_details (card_no,cvv,sort_code,account_no,customer_id) VALUES(?,?,?,?,?);");
            pStmt2.setString(1,cc.cardNumber);
            pStmt2.setString(2,cc.cvv);
            pStmt2.setString(3,cc.sortCode);
            pStmt2.setString(4,cc.accountNumber);
            pStmt2.setInt(5,id);
            pStmt2.executeUpdate();
            pStmt.close();
            pStmt2.close();
            stmt1.close();

            rs.close();
            db.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}