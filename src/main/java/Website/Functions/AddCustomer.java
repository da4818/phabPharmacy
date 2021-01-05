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
            Statement stmt = db.createStatement();
            Statement stmt1 = db.createStatement();
            Statement stmt2 = db.createStatement();
            stmt.execute("INSERT INTO customer (first_name,last_name,email,postcode,address,phone_no) VALUES (cust.first_name,cust.last_name,cust.email,cust.postcode,cust.address,cust.phone_number);");

            String sqlStr = "SELECT * FROM customer WHERE first_name = cust.first_name() AND email = cust.email;";
            ResultSet rs = stmt1.executeQuery(sqlStr);
            if (rs.next()) {
                int id = rs.getInt("id");
            }

            stmt2.execute("INSERT INTO card_details (card_no,cvv,sort_code,account_no,customer_id) VALUES(cc.cardNumber,cc.cvv,cc.sortCode,cc.accountNumber,id);");
            stmt.close();
            stmt1.close();
            stmt2.close();
            rs.close();
            db.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}