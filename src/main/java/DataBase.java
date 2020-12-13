import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class DataBase {
    public String fname;
    public DataBase(){
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
        }
        Connection c = null;
        try {
            c = DriverManager.getConnection(dbUrl);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Statement s = c.createStatement();
            String sql = "CREATE TABLE USERS" +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    "FNAME TEXT NOT NULL," +
                    "LNAME TEXT NOT NULL," +
                    "EMAIL CHAR(50) NOT NULL," +
                    "PASSW CHAR(50) NOT NULL)";
            s.executeUpdate(sql);
            Statement s1 = c.createStatement();
            String sql1 = "INSERT INTO USER (ID,FNAME,LNAME,EMAIL,PASSW) VALUES (1,'John','Doe','johnappleseed@gmail.com','pass123');";
            s1.executeUpdate(sql1);
            Statement s2 = c.createStatement();
            String query = "SELECT * FROM USERS;";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String email = rs.getString("email");
                String pass = rs.getString("passw");
                this.fname = fname;

            }
            rs.close();
            s.close();
            s1.close();
            s2.close();
            c.close();
        } catch (Exception e) {
        }

    }
}


