package Website;

import javax.swing.text.html.HTML;
import java.sql.*;
import java.util.ArrayList;

public class UserDB {
    public String userName;
    public String userEmail;
    public String userPass;
    public String userCardno;
    public String userPostcode;
    public Statement stmt;
    public UserDB() {
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
            /*stmt = c.createStatement();
            String sql ="CREATE TABLE USERS " +
                        "(ID SERIAL PRIMARY KEY NOT NULL," +
                        " FNAME TEXT NOT NULL, " +
                        " LNAME TEXT NOT NULL, " +
                        " EMAIL TEXT NOT NULL, " +
                        " PASSW TEXT NOT NULL, " +
                        " CARDNO TEXT NOT NULL, " +
                        " POSTCODE TEXT NOT NULL)";
            stmt.executeUpdate(sql);*/
            Statement s1 = c.createStatement();
            String sql1 = "INSERT INTO USERS (FNAME,LNAME,EMAIL,PASSW,CARDNO,POSTCODE) VALUES ('Luke','Grenshaw','email1','pass1','1122','HS75UZ');";
            String sql2 = "INSERT INTO USERS (FNAME,LNAME,EMAIL,PASSW,CARDNO,POSTCODE) VALUES ('Gina','Wright','email2','pass2','3344','SW72AZ');";
            s1.executeUpdate(sql1);
            Statement s2 = c.createStatement();
            s2.executeUpdate(sql2);
            stmt.close();
            s1.close();
            s2.close();
            /*Statement s3 = c.createStatement();
            rs = s3.executeQuery("SELECT * FROM USERS;");
            while(rs.next()){
                ids.add( rs.getInt("id"));
                names.add(rs.getString("name"));
                emails.add(rs.getString("email"));
                passes.add(rs.getString("passw"));
            }
            rs.close();
            s3.close();*/
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created");
    }
}

