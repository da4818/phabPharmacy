package Website;

import java.sql.*;

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
    public static Product getProduct(int n){
        Product p = new Product();
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection con=DriverManager.getConnection(dbUrl);

            PreparedStatement ps=con.prepareStatement("select * from products where id=?");
            ps.setInt(1,n);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                p.name = rs.getString("name");
                p.description = rs.getString("description");
                p.price = rs.getDouble("price");
                p.quantity = rs.getInt("quantity");
                p.category = rs.getString("category");
                p.limited = rs.getBoolean("limited");
            }
        }catch(Exception e){System.out.println(e);}
        return p;
    }

    public static void addtoBasket(Product p_in, int quantity_in){
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection con=DriverManager.getConnection(dbUrl);
            PreparedStatement ps=con.prepareStatement("insert into basket (name,description,price,quantity,subtotal) values(?,?,?,?,?)");
            ps.setString(1,p_in.name);
            ps.setString(2,p_in.description);
            ps.setDouble(3,p_in.price);
            ps.setInt(4,quantity_in);
            ps.setDouble(5,p_in.price*quantity_in);

            ps.executeUpdate();
            ps.close();
        }catch(Exception e){System.out.println(e);}
    }
    public static Basket getBasketInfo(int n){
        //[0]=name,[1]=description,[2]=price,[3]=quantity,[4]=subtotal
        Basket basketInfo= new Basket();

        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection con=DriverManager.getConnection(dbUrl);

            PreparedStatement ps=con.prepareStatement("select * from basket where id=?");
            ps.setInt(1,n);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                basketInfo.name = rs.getString("name");
                basketInfo.description =rs.getString("description");
                basketInfo.price = rs.getDouble("price");
                basketInfo.quantity = rs.getInt("quantity");
                basketInfo.subtotal = rs.getDouble("subtotal");
            }
        }catch(Exception e){System.out.println(e);}
        return basketInfo;
    }

    public static void resetTable(String tableName){
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection con=DriverManager.getConnection(dbUrl);
            Statement s =con.createStatement();
            String sql="truncate table " + tableName + ";";
            s.executeUpdate(sql);
            s.close();
        }catch(Exception e){System.out.println(e);}
    }
    public static int tableSize(String tableName){
        int n=0;
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection con=DriverManager.getConnection(dbUrl);
            Statement s =con.createStatement();
            PreparedStatement ps=con.prepareStatement("select count(*) as total from ?");
            ps.setString(1,tableName);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                n = rs.getInt("total");
            }
            s.close();
        }catch(Exception e){System.out.println(e);}
        return n;
    }
}

