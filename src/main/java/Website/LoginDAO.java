package Website;

import java.sql.*;

//DAO = data access object
public class LoginDAO {
    // Functions to regarding creating the databases //
    public static Connection getConnection(){
        Connection c = null;
        try {
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(dbUrl);

        } catch(Exception e){
        }
        return c;
    }
    public static void resetTable(String tableName){
        try{
            Connection c = getConnection();
            Statement s =c.createStatement();
            String sql="truncate table " + tableName + ";";
            s.executeUpdate(sql);
            s.close();
        }catch(Exception e){System.out.println(e);}
    }

    // Functions to execute queries, or amend to the database content //
    // Checking if user is logging in with an existing account //
    public static boolean validateLogin(String email_in,String pass_in){
        boolean status=false;
        try{
            Connection c = getConnection();
            PreparedStatement ps=c.prepareStatement("select * from users where email=? and passw=?");
            ps.setString(1,email_in);
            ps.setString(2,pass_in);

            ResultSet rs=ps.executeQuery();
            status=rs.next();

        }catch(Exception e){System.out.println(e);}
        return status;
    }
    // Checking if user is registering with an existing email address //
    public static boolean validateRegister(String email_in){
        boolean status=false;
        try{
            Connection c = getConnection();

            PreparedStatement ps=c.prepareStatement("select * from users where email=?");
            ps.setString(1,email_in);

            ResultSet rs=ps.executeQuery();
            status=rs.next();

        }catch(Exception e){System.out.println(e);}
        return status;
    }

    public static User getUser(String email_in,String pass_in){
        User u = new User();
        try{
            Connection c = getConnection();

            PreparedStatement ps=c.prepareStatement("select * from users where email=? and passw=?");
            ps.setString(1,email_in);
            ps.setString(2,pass_in);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                u.fname = rs.getString("fname");
                u.lname = rs.getString("name");
                u.email = rs.getString("email");
                u.password = rs.getString("passw");
                u.cardno = rs.getString("cardno");
                u.postcode = rs.getString("postcode");
            }
        }catch(Exception e){System.out.println(e);}
        return u;
    }
    public static void addUser(String fname_in,String lname_in, String email_in,String pass_in, String cardno_in, String postcode_in){
        try{
            Connection c = getConnection();

            PreparedStatement ps=c.prepareStatement("insert into users (fname,lname,email,passw,cardno,postcode) values(?,?,?,?,?,?)");
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

    // Gets product attributes to display on browse page //
    public static Product getProduct(int n){
        Product p = new Product();
        try{
            Connection c = getConnection();
            PreparedStatement ps=c.prepareStatement("select * from products where id=?");
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

    // Adds product to a basket table //
    public static void addToBasket(Product p_in, int quantity_in){
        try{
            Connection c = getConnection();
            PreparedStatement ps=c.prepareStatement("insert into basket (name,description,price,quantity,subtotal) values(?,?,?,?,?)");
            ps.setString(1,p_in.name);
            ps.setString(2,p_in.description);
            ps.setDouble(3,p_in.price);
            ps.setInt(4,quantity_in);
            ps.setDouble(5,p_in.price*quantity_in);

            ps.executeUpdate();
            ps.close();
        }catch(Exception e){System.out.println(e);}
    }

    // Gets info from products added to basket to display on basket page //
    public static Basket getBasketInfo(int n){
        Basket bProduct= new Basket();

        try{
            Connection c = getConnection();
            PreparedStatement ps=c.prepareStatement("select * from basket where id=?");
            ps.setInt(1,n);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                bProduct.name = rs.getString("name");
                bProduct.description =rs.getString("description");
                bProduct.price = rs.getDouble("price");
                bProduct.quantity = rs.getInt("quantity");
                bProduct.subtotal = rs.getDouble("subtotal");
            }
        }catch(Exception e){System.out.println(e);}
        return bProduct;
    }


    // Display size of table - i.e. number of entries //
    public static int tableSize(String tableName){
        int n=0;
        try{
            Connection c = getConnection();
            String sql = "select count(*) from " + tableName;
            Statement s =c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                String p = rs.getString(1);
                n=Integer.parseInt(p);
            }
            s.close();
        }catch(Exception e){System.out.println(e);}
        return n;
    }
}

