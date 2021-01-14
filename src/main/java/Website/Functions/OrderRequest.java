package Website.Functions;

import java.sql.*;
import java.util.List;
//This class uses a customers ID to identify any online orders they have made and stores them as as an Order
public class OrderRequest {
    Order order;
    List<Product> products;
    public OrderRequest(int customerId) {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try {
            Class.forName("org.postgresql.Driver");
            Connection db = DriverManager.getConnection(dbUrl);
            Statement stmt = db.createStatement();
            String sqlStr2 = "SELECT * FROM ordered_product WHERE customer_id = " + customerId;
            ResultSet rs2 = stmt.executeQuery(sqlStr2);
            while (rs2.next()) {
                Product p = new Product(rs2.getInt("barcode"), rs2.getString("name"), rs2.getString("brand"), rs2.getInt("quantity"), rs2.getString("category"));
                products.add(p);
                System.out.println(rs2.getString("name"));
            }
            this.order = new Order(products, customerId);
            rs2.close();
            stmt.close();
            db.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Product> getOrder() {
        return products;
    }
}
