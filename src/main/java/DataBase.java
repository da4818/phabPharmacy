import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class DataBase {
    public String fname;
    public DataBase(){
        Statement s,s1,s2 = null;
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
            s = c.createStatement();
            String sql = "CREATE TABLE ACCOUNTS " +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " FNAME VARCHAR(50) NOT NULL, " +
                    " LNAME VARCHAR(50) NOT NULL, " +
                    " EMAIL VARCHAR(50) NOT NULL, " +
                    " PASSW VARCHAR(50) NOT NULL)";
            s.executeUpdate(sql);
            s1 = c.createStatement();
            String sql1 = "INSERT INTO ACCOUNTS (ID,FNAME,LNAME,EMAIL,PASSW) VALUES (1,'John','Doe','johnappleseed@gmail.com','pass123');";
            s1.executeUpdate(sql1);
            s2 = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM ACCOUNTS;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("fname");
                this.fname = name;
                String lname = rs.getString("lname");
                String email = rs.getString("email");
                String pass = rs.getString("passw");
            }
            rs.close();
            s.close();
            s1.close();
            s2.close();
            c.close();
        } catch (SQLException throwables) {
        }

    }

}



