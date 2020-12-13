import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class DataBase {
    public String fname;
    public String lname;
    public String email;
    public String pass;
    public DataBase(){
        Connection c = null;
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(dbUrl);
            Statement s = c.createStatement();
            String sql = "CREATE TABLE USER" +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    "FNAME TEXT NOT NULL," +
                    "LNAME TEXT NOT NULL," +
                    "EMAIL CHAR(50) NOT NULL," +
                    "PASS CHAR(50) NOT NULL)";
            s.executeUpdate(sql);
            Statement s1 = c.createStatement();
            String sql1 = "INSERT INTO USER (ID,FNAME,LNAME,EMAIL,PASS) VALUES (1,'John','Doe','johnappleseed@gmail.com','pass123');";
            s1.executeUpdate(sql1);
            Statement s2 = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM USER;");
            while (rs.next()) {
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String email = rs.getString("email");
                String pass = rs.getString("pass");
                this.fname = fname;
                this.lname = lname;
                this.email = email;
                this.pass = pass;
            }
            rs.close();
            s.close();
            s1.close();
            s2.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created");
    }
}
