package Website;
import Website.Entities.Product;
import Website.Entities.User;
import java.sql.*;

// DAO = data access object //
// all comments with a * at the beginning are referring to any changes that need to be made in terms of the databases Choi Wan has made
public class LoginDAO {

    //Empties tables - useful for when an order has been made and the basket needs to be emptied
    public static void resetTable(String tableName){
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s = c.createStatement();
            String sql = "truncate table " + tableName + ";";
            s.executeUpdate(sql);
            s.close();
        }catch(Exception e){System.out.println(e);}
    }
    public static void createTable(String tableName){
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s = c.createStatement();
            Statement s1 = c.createStatement();
            if(tableName.equals("users")) { //* rename to 'customers'
                String sql ="CREATE TABLE USERS " +
                        "(ID SERIAL PRIMARY KEY NOT NULL," +
                        " FNAME TEXT NOT NULL, " +
                        " LNAME TEXT NOT NULL, " +
                        " EMAIL TEXT NOT NULL, " +
                        " PASSW TEXT NOT NULL, " + //*add password to 'customer' table
                        " CARDNO TEXT NOT NULL, " +
                        " POSTCODE TEXT NOT NULL)"; //*add phone_no VARCHAR(12), address VARCHAR(128) to table
                s.executeUpdate(sql);
                s1.executeUpdate("INSERT INTO USERS (FNAME,LNAME,EMAIL,PASSW,CARDNO,POSTCODE) VALUES ('John','Doe','email1','pass1','cardno1','SW72AZ');");
                s1.executeUpdate("INSERT INTO USERS (FNAME,LNAME,EMAIL,PASSW,CARDNO,POSTCODE) VALUES ('Mia','Stewart','email2','pass2','cardno2','SW65TD');");
            }
            else if(tableName.equals("products")){ //*rename to 'shop_products'
                String sql ="CREATE TABLE PRODUCTS " +
                        "(ID SERIAL PRIMARY KEY NOT NULL," +
                        " NAME TEXT NOT NULL, " + // *separate into 'brand' and 'name'
                        " DESCRIPTION TEXT NOT NULL, " + //*rename to 'amount'
                        " PRICE DOUBLE PRECISION NOT NULL, " +
                        " QUANTITY INTEGER NOT NULL, " +
                        " CATEGORY TEXT NOT NULL, " +
                        " LIMITED BOOLEAN NOT NULL)"; //*I think boolean may be easier to manage than SMALLINT
                //*add other columns (buy_price,soft/hard min)
                s.executeUpdate(sql);
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Vicks Vaporub','100g',4.50,15,'Cold and Flu',false);");
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Vicks First Defence','15ml',6.80,20,'Cold and Flu',false);");
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Gsk Night Nurse','160ml',8.50,30,'Cold and Flu',false);");
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Gsk Night Nurse','160ml',9.00,30,'Cold and Flu',false);");
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Lemsip Max','16 caps',4.20,25,'Cold and Flu',false);");
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Lemsip Standard','10 sachets',4.50,25,'Cold and Flu',false);");
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Sudafed Day & Night','16 caps',4.20,30,'Cold and Flu',true);");
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Sudafed Max','16 caps',4.80,30,'Cold and Flu',true);");
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Benylin Mucus Relief','16 caps',4.80,20,'Cold and Flu',false);");
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Benylin 4 Flu','24 caps',6.00,20,'Cold and Flu',false);");
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('E45 Psoriasis Cream','50ml',20.00,15,'Skincare',false);");
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Eurax Skin Cream','100g',5.70,15,'Skincare',false);");
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Nurofen Meltlets','16 caps',4.00,40,'Headaches and Pain Relief',false);");
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Dioralyte Blackcurrant','12 sachets',8.00,20,'Digestion',false);");
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Benadryl Relief','24 caps',9.00,20,'Allergy',false);");
                s1.executeUpdate("INSERT INTO PRODUCTS (NAME,DESCRIPTION,PRICE,QUANTITY,CATEGORY,LIMITED) VALUES ('Dettol Antiseptic','500ml',3.20,20,'First Aid',false);");
            }
            else if(tableName.equals("basket")){ //*rename to 'ordered_products' (i think)
                String sql ="CREATE TABLE BASKET " +
                        "(ID INT NOT NULL," +
                        " NAME TEXT NOT NULL, " +
                        " DESCRIPTION TEXT NOT NULL, " +
                        " PRICE DOUBLE PRECISION NOT NULL, " + //* rename to 'sell_price'
                        " QUANTITY INTEGER NOT NULL, " +
                        " SUBTOTAL TEXT NOT NULL, " +
                        " LIMITED BOOLEAN NOT NULL)";
                s.executeUpdate(sql); //*we may need to add the customer's name to this table so that we can manage multiple baskets at once
            }
            else if(tableName.equals("orders")){ //*this orders table may be redundant as the values are the same as the basket
                String sql ="CREATE TABLE ORDERS " +
                        "(ID SERIAL PRIMARY KEY NOT NULL," +
                        " NAME TEXT NOT NULL, " +
                        " DESCRIPTION TEXT NOT NULL, " +
                        " PRICE DOUBLE PRECISION NOT NULL, " +
                        " QUANTITY INTEGER NOT NULL, " +
                        " SUBTOTAL TEXT NOT NULL, " +
                        " LIMITED BOOLEAN NOT NULL)";
                s.executeUpdate(sql);
                s1.executeUpdate("INSERT INTO ORDERS (ID,NAME,DESCRIPTION,PRICE,QUANTITY,SUBTOTAL,LIMITED) SELECT ID,NAME,DESCRIPTION,PRICE,QUANTITY,SUBTOTAL,LIMITED FROM BASKET;");
            }
            else if(tableName.equals("logged")) { //this table is so that we can see which customer is currently logged in - there will only be at most 1 entry in this table, and will be updated when a new user logs in
                String sql ="CREATE TABLE LOGGED " +
                        "(ID SERIAL PRIMARY KEY NOT NULL," +
                        " FNAME TEXT NOT NULL, " +
                        " LNAME TEXT NOT NULL, " +
                        " EMAIL TEXT NOT NULL, " +
                        " PASSW TEXT NOT NULL, " +
                        " CARDNO TEXT NOT NULL, " +
                        " POSTCODE TEXT NOT NULL)";
                s.executeUpdate(sql);
            }
            s.close();
            s1.close();
            c.close();
        }catch(Exception e){System.out.println(e);}
    }
    // Functions to execute queries, or amend to the database content //
    // Checking if user is logging in with an existing account
    public static boolean validateLogin(String email_in,String pass_in){
        boolean status=false;
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            PreparedStatement ps=c.prepareStatement("select * from users where email=? and passw=?");
            ps.setString(1,email_in);
            ps.setString(2,pass_in);
            ResultSet rs = ps.executeQuery();
            status = rs.next(); //Status is now true if an entry with the input email and password exists
            c.close();
        }catch(Exception e){System.out.println(e);}
        return status;
    }
    // Checking if user is registering with an existing email address
    public static boolean validateRegister(String email_in){
        boolean status=false;
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            PreparedStatement ps=c.prepareStatement("select * from users where email=?");
            ps.setString(1,email_in);
            ResultSet rs=ps.executeQuery();
            status = rs.next(); //similar to line 121
            ps.close();
            c.close();
        }catch(Exception e){System.out.println(e);}
        return status;
    }

    //Looks for user entry with the specific email and password, then returns this as a User class (similar variables to the table)
    public static User getUser(String email_in, String pass_in){
        User u = new User();
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            PreparedStatement ps=c.prepareStatement("select * from users where email=? and passw=?");
            ps.setString(1,email_in);
            ps.setString(2,pass_in);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                u.id = rs.getInt("id");
                u.fname = rs.getString("fname");
                u.lname = rs.getString("name");
                u.email = rs.getString("email");
                u.password = rs.getString("passw");
                u.cardno = rs.getString("cardno");
                u.postcode = rs.getString("postcode");
            }
            ps.close();
            c.close();
        }catch(Exception e){System.out.println(e);}
        return u;
    }
    //If registration is valid, the customer's information will be added to the database
    public static void addUser(String fname_in,String lname_in, String email_in,String pass_in, String cardno_in, String postcode_in){
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            PreparedStatement ps=c.prepareStatement("insert into users (fname,lname,email,passw,cardno,postcode) values(?,?,?,?,?,?)");
            ps.setString(1,fname_in);
            ps.setString(2,lname_in);
            ps.setString(3,email_in);
            ps.setString(4,pass_in);
            ps.setString(5,cardno_in);
            ps.setString(6,postcode_in);
            ps.executeUpdate();
            ps.close();
            c.close();
        }catch(Exception e){System.out.println(e);}
    }
    //This function is to see who is currently logged into the website - in reality this probably won't work if multiple people are using the website at once
    public static User getCurrentUser(){
        User u = new User();
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s=c.createStatement();
            ResultSet rs=s.executeQuery("select * from logged;");
            while(rs.next()){
                u.id = rs.getInt("id");
                u.fname = rs.getString("fname");
                u.lname = rs.getString("lname");
                u.email = rs.getString("email");
                u.password = rs.getString("passw");
                u.cardno = rs.getString("cardno");
                u.postcode = rs.getString("postcode");
            }
            s.close();
            c.close();
        }catch(Exception e){System.out.println(e);}
        return u;
    }
    //When a customer logs in/registers, it will update the table holding the info of the current user to their details
    public static void setLoggedInUser(User loggedInUser){
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s=c.createStatement();
            s.executeUpdate("truncate table logged"); //instead of updating the table it will just empty it and add a new entry
            String sql = "INSERT INTO LOGGED (ID,FNAME,LNAME,EMAIL,PASSW,CARDNO,POSTCODE) SELECT ID,FNAME,LNAME,EMAIL,PASSW,CARDNO,POSTCODE FROM USERS WHERE ID=" +loggedInUser.id +";";
            s.executeUpdate(sql);
            s.close();
            c.close();
        }catch(Exception e){System.out.println(e);}
    }
    //this is to see the customer is currently logged in
    public static boolean checkLoggedIn(){
        boolean status=false;
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s=c.createStatement();
            ResultSet rs=s.executeQuery("select * from logged");
            status=rs.next(); //Status is now true if an entry with the email and password exists (i.e. the only entry in the table)
            c.close();

        }catch(Exception e){System.out.println(e);}
        return status;
    }

    // Gets product attributes to display on browse page
    public static Product getProduct(int n){
        Product p = new Product();
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            PreparedStatement ps=c.prepareStatement("select * from products where id=?");
            ps.setInt(1,n);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                p.id = rs.getInt("id");
                p.name = rs.getString("name");
                p.description = rs.getString("description");
                p.price = rs.getDouble("price");
                p.quantity = rs.getInt("quantity");
                p.category = rs.getString("category");
                p.limited = rs.getBoolean("limited");
            }
            ps.close();
            c.close();

        }catch(Exception e){System.out.println(e);}
        return p;
    }

    // Adds product to a basket table
    public static void addToBasket(Product p_in, int quantity_in){
        try{ //if the customer adds Vicks vaporub x1 and then adds the same product but x2 again, we want it to display 'x2' rather than 'x1' and 'x2' appearing separately
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            String sql = "select * from basket where name='" + p_in.name + "';"; //we check if the user has previous added the item to the basket before
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){ //if they have, we will update the quantity to the most recent value they have chosen (it won't add the amount e.g. if they click x1 and then x3 it updates to x3, not x4 - this is for simplicity)
                Statement s1 = c.createStatement();
                String sql1 = "update basket set quantity =" + quantity_in + "where name='" + p_in.name+ "'";
                s1.executeUpdate(sql1);
                s1.close();
            }
            else { //if they haven't previously added the item to the basket, it will create a new entry in the table
                PreparedStatement ps = c.prepareStatement("insert into basket (id,name,description,price,quantity,subtotal,limited) values(?,?,?,?,?,?,?)");
                ps.setInt(1,p_in.id);
                ps.setString(2, p_in.name);
                ps.setString(3, p_in.description);
                ps.setDouble(4, p_in.price);
                ps.setInt(5, quantity_in); //Quantity added to basket rather than full stock quantity
                ps.setDouble(6, p_in.price * quantity_in);
                ps.setBoolean(7, p_in.limited);
                ps.executeUpdate();
                ps.close();
            }
            s.close();
            c.close();
        }catch(Exception e){System.out.println(e);}
    }

    // Gets info from products added to basket to display on basket page, based on the position it appears on the page (line 301)
    public static Product getBasketInfo(int n){
        Product bProduct= new Product();
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            PreparedStatement ps=c.prepareStatement("with temp as (select row_number() over (order by name asc) as rownum, * from basket) select * from temp where rownum=?");
            ps.setInt(1,n); //If one item is removed, the IDs aren't automatically updated e.g. if i remove item ID=2, table's ID will read as 1,3,4,5... this poses problems when using a for loop to display the information
            ResultSet rs=ps.executeQuery(); //SQL has no easy way to select item based on row number rather than an existing column - this is one solution
            while(rs.next()){ // If int n = 2 (i.e. the second item in the basket), this will correspond to the 2nd entry in the basket table based on alphabetical order ('order by name asc' gives alphabetical order)
                bProduct.id = rs.getInt("id");
                bProduct.name = rs.getString("name");
                bProduct.description =rs.getString("description");
                bProduct.price = rs.getDouble("price");
                bProduct.quantity = rs.getInt("quantity");
                bProduct.limited = rs.getBoolean("limited");
            }
            ps.close();
            c.close();
        }catch(Exception e){System.out.println(e);}
        return bProduct;
    }
    public static Product returnBasketInfo(int n){
        Product bProduct= new Product();
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            PreparedStatement ps=c.prepareStatement(" select * from basket where id=?");
            ps.setInt(1,n); //If one item is removed, the IDs aren't automatically updated e.g. if i remove item ID=2, table's ID will read as 1,3,4,5... this poses problems when using a for loop to display the information
            ResultSet rs=ps.executeQuery(); //SQL has no easy way to select item based on row number rather than an existing column - this is one solution
            while(rs.next()){ // If int n = 2 (i.e. the second item in the basket), this will correspond to the 2nd entry in the basket table based on alphabetical order ('order by name asc' gives alphabetical order)
                bProduct.id = rs.getInt("id");
                bProduct.name = rs.getString("name");
                bProduct.description =rs.getString("description");
                bProduct.price = rs.getDouble("price");
                bProduct.quantity = rs.getInt("quantity");
                bProduct.limited = rs.getBoolean("limited");
            }
            ps.close();
            c.close();
        }catch(Exception e){System.out.println(e);}
        return bProduct;
    }

    public static Double getBasketTotal(){
        double total = 0;
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            String sql = "select sum(price*quantity) from basket"; //For some reason it won't return the existing value in the subtotal column
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                total = rs.getDouble(1);
            }
            s.close();
            c.close();
        }catch(Exception e){System.out.println(e);}
        return total;
    }
    //Removes item from the basket database when trash icon is pressed
    public static void removeFromBasket(int id_in){
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s = c.createStatement();
            String sql = "delete from basket where id=" + id_in; //removes the item entry from the table
            s.executeUpdate(sql);

            s.close();
        }catch(Exception e){System.out.println(e);}
    }
    // Display size of table - i.e. number of entries - used as a for loop in displaying all the products in the browse page
    //Also useful when determining whether tables are empty
    public static int tableSize(String tableName){
        int n=0; //base case - n = 0 tells us the table is empty or something has gone wrong
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            String sql = "select count(*) from " + tableName + ";"; //'select count(*)' gets the number of entries
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                String p = rs.getString(1); //'select count' returns a string value, not a number
                n=Integer.parseInt(p); //so we convert that string to an integer
            }
            s.close();
            c.close();
        }catch(Exception e){System.out.println(e);}
        return n;
    }
    // To return the number of items in the basket - to be displayed on the navigation bar header
    public static int getBasketSize(){
        int n=0;
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            String sql = "select sum(quantity) from basket";//although similar to 'tableSize()', this counts the number of items, not just the number of different products
            Statement s = c.createStatement(); //i.e. x3 vicks and x2 dettol is 5 items comprised of 2 different products - the basket displays 5
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                String p = rs.getString(1);
                n=Integer.parseInt(p);
            }
            s.close();
            c.close();
        }catch(Exception e){System.out.println(e);}
        return n;
    }
}