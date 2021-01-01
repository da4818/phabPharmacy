package Website.Entities;

import javax.swing.text.html.HTML;
import java.sql.*;
import java.util.ArrayList;

public class BasketDB {
    public ArrayList<Integer> ids;
    public ArrayList <String> names;
    public ArrayList <String> emails;
    public ArrayList <String> passes;
    public Statement stmt;
    public BasketDB() {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
        }
        Connection c = null;
        try {
            c = DriverManager.getConnection(dbUrl);
        } catch (SQLException throwables) {
        }

        try {
            stmt = c.createStatement();
            String sql ="CREATE TABLE BASKET " +
                    "(ID SERIAL PRIMARY KEY NOT NULL," +
                    " NAME TEXT NOT NULL, " +
                    " DESCRIPTION TEXT NOT NULL, " +
                    " PRICE DOUBLE PRECISION NOT NULL, " +
                    " QUANTITY INTEGER NOT NULL, " +
                    " SUBTOTAL TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created");
    }
}

