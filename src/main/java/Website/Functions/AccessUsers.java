package Website.Functions;

import java.sql.*;

public class AccessUsers {
    String output;
    public AccessUsers(String username, String password){
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try {
            Class.forName("org.postgresql.Driver");
            //Making connection to our database
            Connection db = DriverManager.getConnection(dbUrl);
            Statement stmt = db.createStatement();
            String sqlStr = "SELECT * FROM staff WHERE username = " + username;
            password = password.replace(" ","");
            System.out.println(password);
            ResultSet rs = stmt.executeQuery(sqlStr);
            if (rs.next()) {
                String password1 = rs.getString("password");
                if ((password) == password1){
                    this.output = "true";
                    System.out.println("password check passed");
                }
                else{
                    this.output = "false";
                    System.out.println("password check failed");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public String getOutput() {
        return output;
    }
}
