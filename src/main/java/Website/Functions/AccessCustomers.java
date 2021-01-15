package Website.Functions;

import java.sql.*;
/*This class is used by the CustomerServlet to retrieve a list of all customers from the online store */
public class AccessCustomers {
    Customers customers;
    public AccessCustomers(){
        //Establishes connection to out database
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        this.customers = new Customers();
            try {
                Class.forName("org.postgresql.Driver");
                Connection db = DriverManager.getConnection(dbUrl);
                Statement stmt = db.createStatement();
                String sqlStr = "SELECT * FROM customer";
                //Here we select all the customers and make a list of them to be used by other classes
                ResultSet rs = stmt.executeQuery(sqlStr);
                while (rs.next()) {
                    //String address, String email, String first_name, int id, String last_name, String phone_no, String postcode){
                    Customer customer = new Customer(rs.getString("address"),rs.getString("email"),rs.getString("first_name"),rs.getInt("id"),rs.getString("last_name"),rs.getString("phone_no"),rs.getString("postcode"));
                    System.out.println("created customer");
                    this.customers.addCustomers(customer);
                }
                rs.close();
                stmt.close();
                db.close();
                System.out.println("values accessed");
        }
            catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public Customers getCustomers() {
        return customers;
    }
}
