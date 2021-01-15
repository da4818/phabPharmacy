package Website.Functions;

import java.sql.*;
//This class simply uses a string formatted date to extract the profit on that date from our database
public class ProfitRequest{
    public float profit;
    public ProfitRequest(String date){
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try {
            Class.forName("org.postgresql.Driver");
            Connection db = DriverManager.getConnection(dbUrl);
            Statement stmt = db.createStatement();
            String sqlStr = "SELECT * FROM daily_profit WHERE date = " + date + ";";
            System.out.println(sqlStr);
            ResultSet rs = stmt.executeQuery(sqlStr);
            if (rs.next()) {
                profit = rs.getFloat("profit");
                System.out.println(profit);
            }
            else {
                profit = 0f;
            }
            rs.close();
            stmt.close();
            db.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
