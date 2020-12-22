package Website;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

//DAO = data access object
public class LoginDAO {
    public static boolean validate(String email_in,String pass_in){
        boolean status=false;
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(dbUrl);

            PreparedStatement ps=con.prepareStatement("select * from users where email=? and passw=?");
            ps.setString(1,email_in);
            ps.setString(2,pass_in);

            ResultSet rs=ps.executeQuery();
            status=rs.next();

        }catch(Exception e){System.out.println(e);}
        return status;
    }
    public static String getName(String email_in,String pass_in){
        String userName="";
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection con=DriverManager.getConnection(dbUrl);

            PreparedStatement ps=con.prepareStatement("select * from users where email=? and passw=?");
            ps.setString(1,email_in);
            ps.setString(2,pass_in);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                userName = rs.getString("fname");
            }
        }catch(Exception e){System.out.println(e);}
        return userName;
    }
    public static void addUser(String fname_in,String lname_in, String email_in,String pass_in, String cardno_in, String postcode_in){
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection con=DriverManager.getConnection(dbUrl);

            PreparedStatement ps=con.prepareStatement("insert into users (fname,lname,email,passw,cardno,postcode) values(?,?,?,?,?,?)");
            ps.setString(1,fname_in);
            ps.setString(2,lname_in);
            ps.setString(3,email_in);
            ps.setString(4,pass_in);
            ps.setString(5,cardno_in);
            ps.setString(6,postcode_in);

            ps.executeUpdate();
            ps.close();
        }catch(Exception e){System.out.println(e);}
    }
    public static ProductInfo getProductInfo(int n){
        ProductInfo pi = new ProductInfo();

        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection con=DriverManager.getConnection(dbUrl);

            PreparedStatement ps=con.prepareStatement("select * from products where id=?");
            ps.setInt(1,n+1);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                pi.name = rs.getString("name");
                pi.description = rs.getString("description");
                pi.price = rs.getDouble("price");
                pi.quantity = rs.getInt("quantity");
                pi.category = rs.getString("category");
                pi.limited = rs.getBoolean("limited");
            }
            DecimalFormat df = new DecimalFormat("0.00");
            pi.price = Double.valueOf(df.format(pi.price));

        }catch(Exception e){System.out.println(e);}
        return pi;
    }

}

