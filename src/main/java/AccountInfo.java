import javax.lang.model.type.ArrayType;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;


public class AccountInfo {
    public ArrayList <String> names;
    public ArrayList <String> familynames;
    public ArrayList <String> users;
    public ArrayList <String> emails;
    public ArrayList <String> passwords;
    public AccountInfo(){
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
            String sql = "CREATE TABLE ACCOUNT " +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " FNAME VARCHAR(50) NOT NULL, " +
                    " LNAME VARCHAR(50) NOT NULL, " +
                    " USER VARCHAR(50) NOT NULL, " +
                    " EMAIL VARCHAR(50) NOT NULL, " +
                    " PASSW VARCHAR(50) NOT NULL)";
            s.executeUpdate(sql);
            s1 = c.createStatement();
            String sql1 = "INSERT INTO ACCOUNT (ID,FNAME,LNAME,USER,EMAIL,PASSW) VALUES (1,'John','Doe','jdoe','johnappleseed@gmail.com','pass123');";
            String sql1a = "INSERT INTO ACCOUNT (ID,FNAME,LNAME,USER,EMAIL,PASSW) VALUES (2,'Tom','Scott','scottt','ts53@gmail.com','qwerty');";
            s1.executeUpdate(sql1);
            Statement s1a = c.createStatement();
            s1a.executeUpdate(sql1a);
            s2 = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM ACCOUNT;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("fname");
                names.add(name);
                String lname = rs.getString("lname");
                familynames.add(lname);
                String user = rs.getString("user");
                users.add(user);
                String email = rs.getString("email");
                emails.add(email);
                String pass = rs.getString("passw");
                passwords.add(pass);
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



