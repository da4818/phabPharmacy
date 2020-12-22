package Website;

import javax.swing.text.html.HTML;
import java.sql.*;
import java.util.ArrayList;

public class ProductDB {
    public ArrayList<Integer> ids;
    public ArrayList <String> names;
    public ArrayList <String> emails;
    public ArrayList <String> passes;
    public Statement stmt;
    public ProductDB() {
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
            String sql ="CREATE TABLE PRODUCTS " +
                    "(ID SERIAL PRIMARY KEY NOT NULL," +
                    " NAME TEXT NOT NULL, " +
                    " DESCRIPTION TEXT NOT NULL, " +
                    " PRICE DOUBLE PRECISION NOT NULL, " +
                    " QUANTITY INTEGER NOT NULL, " +
                    " CATEGORY TEXT NOT NULL, " +
                    " LIMITED BOOLEAN NOT NULL)";
            stmt.executeUpdate(sql);
            Statement s1 = c.createStatement();
            String sql1 = "INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Vicks Vaporub','100g',4.50,15,'Cold and Flu',false);";
            s1.executeUpdate(sql1);
            sql1 = "INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Vicks First Defence','15ml',6.80,20,'Cold and Flu',false);";
            s1.executeUpdate(sql1);
            sql1 = "INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Gsk Night Nurse','160ml',8.50,30,'Cold and Flu',false);";
            s1.executeUpdate(sql1);
            sql1 = "INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Gsk Night Nurse','160ml',9.00,30,'Cold and Flu',false);";
            s1.executeUpdate(sql1);
            sql1 = "INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Lemsip Max','16 caps',4.20,25,'Cold and Flu',false);";
            s1.executeUpdate(sql1);
            sql1 = "INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Lemsip Standard','10 sachets',4.50,25,'Cold and Flu',false);";
            s1.executeUpdate(sql1);
            sql1 = "INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Sudafed Day & Night','16 caps',4.20,30,'Cold and Flu',true);";
            s1.executeUpdate(sql1);
            sql1 = "INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Sudafed Max','16 caps',4.80,30,'Cold and Flu',true);";
            s1.executeUpdate(sql1);
            sql1 = "INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Benylin Mucus Relief','16 caps',4.80,20,'Cold and Flu',false);";
            s1.executeUpdate(sql1);
            sql1 = "INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Benylin 4 Flu','24 caps',6.00,20,'Cold and Flu',false);";
            s1.executeUpdate(sql1);
            stmt.close();
            s1.close();

            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created");
    }
}
