package Website;
import Website.Entities.CreditCard;
import Website.Entities.Product;
import Website.Entities.User;
import Website.Functions.UpdateQuantity;

import java.sql.*;

import static java.lang.String.valueOf;

// DAO = data access object //
// all comments with a * at the beginning are referring to any changes that need to be made in terms of the databases Choi Wan has made
public class LoginDAO {

    //Empties tables - useful for when an order has been made and the basket needs to be emptied
    public static void resetTable(String tableName){
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s = c.createStatement();
            String sql = "truncate table " + tableName + ";";
            s.executeUpdate(sql);
            s.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
    }
    public static void createTable(String tableName){
        Statement sCustomer = null;
        Statement sCustomer1 = null;
        Statement sProduct = null;
        Statement sProduct1 = null;
        Statement sBasket = null;
        Statement sLogged = null;
        Statement s = null;
        Statement s1 = null;
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);

            if(tableName.equals("customer")) {
                sCustomer = c.createStatement();
                String sqlCustomer = "CREATE TABLE CUSTOMER (" +
                        "ID SERIAL PRIMARY KEY NOT NULL," +
                        "FIRST_NAME VARCHAR(36) NOT NULL, " +
                        "LAST_NAME VARCHAR(36) NOT NULL, " +
                        "EMAIL VARCHAR(256) NOT NULL, " +
                        "PASSWORD VARCHAR(256) NOT NULL, " +
                        "POSTCODE VARCHAR(8) NOT NULL, " +
                        "ADDRESS VARCHAR(128), " +
                        "PHONE_NO VARCHAR(12))";
                sCustomer.executeUpdate(sqlCustomer);

                sCustomer1 = c.createStatement();
                sCustomer1.executeUpdate("INSERT INTO CUSTOMER(FIRST_NAME,LAST_NAME,EMAIL,PASSWORD,POSTCODE) VALUES('CW','Yip','cwy@gmail.com','password1','SW7 2AZ');");
                sCustomer1.executeUpdate("INSERT INTO CUSTOMER(FIRST_NAME,LAST_NAME,EMAIL,PASSWORD,POSTCODE) VALUES('John','Smith','js@hotmail.com','qwerty','TW6 1AP');");
            }

            else if(tableName.equals("shop_product")){
                sProduct = c.createStatement();
                String sqlBranch = "CREATE TABLE BRANCH (" +
                        "ID SERIAL PRIMARY KEY NOT NULL, " +
                        "NAME VARCHAR(36) NOT NULL );";
                sProduct.executeUpdate(sqlBranch);
                sProduct.executeUpdate("INSERT INTO BRANCH(NAME) VALUES('Paddington');" +
                        "INSERT INTO BRANCH(NAME) VALUES('Green Park');" +
                        "INSERT INTO BRANCH(NAME) VALUES('Mile End');");

                String sqlProduct = "CREATE TABLE SHOP_PRODUCT (" +
                        "BARCODE SERIAL PRIMARY KEY NOT NULL, " +
                        "CATEGORY VARCHAR(36) NOT NULL, " +
                        "BRAND VARCHAR(36) NOT NULL, " +
                        "NAME VARCHAR(36) NOT NULL, " +
                        "AMOUNT VARCHAR(36) NOT NULL, " +
                        "SELL_PRICE DECIMAL(10,2) NOT NULL, " +
                        "BUY_PRICE DECIMAL(10,2) NOT NULL, " +
                        "QUANTITY SMALLINT NOT NULL, " +
                        "FULL_STOCK SMALLINT NOT NULL, " +
                        "LIMIT_OF_1 BOOLEAN NOT NULL," +
                        "SOFT_MIN DECIMAL(10,2)," +
                        "HARD_MIN DECIMAL(10,2)," +
                        "BRANCH_ID INT REFERENCES BRANCH (ID))";

                sProduct.executeUpdate(sqlProduct); //"name" appears to be a keyword in SQL (highlighted in orange for the "insert into" command), so it won't allow me to create the table with it for some reason (works with branch table though)
                sProduct1 = c.createStatement();
                sProduct1.executeUpdate("INSERT INTO SHOP_PRODUCT(CATEGORY,BRAND,NAME,AMOUNT,SELL_PRICE,BUY_PRICE,QUANTITY,FULL_STOCK,LIMIT_OF_1) VALUES('Cold and Flu','Vicks','Vaporub','100g',4.5,3.7,15,15,false)," +
                        "('Cold and Flu','Vicks','First Defence','15ml',6.8,5,20,20,false)," +
                        "('Cold and Flu','Gsk','Night Nurse','160ml',8.5,7,30,30,false)," +
                        "('Cold and Flu','Gsk','Night Nurse','160ml',9,7.5,30,30,false)," +
                        "('Cold and Flu','Lemsip','Max','16 caps',4.2,3.7,25,25,false)," +
                        "('Cold and Flu','Lemsip','Standard','10 sachets',4.5,3.5,25,25,false)," +
                        "('Cold and Flu','Sudafed','Day and Night','16 caps',4.5,3.2,30,30,true)," +
                        "('Cold and Flu','Sudafed','Max','16 caps',4.2,3.2,30,30,true)," +
                        "('Cold and Flu','Benylin','Mucus relief','16 caps',4.8,3.2,20,20,false)," +
                        "('Cold and Flu','Benylin','4 flu','24 caps',6,4.9,20,20,false);");
                
                sProduct1.executeUpdate("INSERT INTO SHOP_PRODUCT(CATEGORY,BRAND,NAME,AMOUNT,SELL_PRICE,BUY_PRICE,QUANTITY,FULL_STOCK,LIMIT_OF_1) VALUES('Skincare','E45','Psoriasis cream','50ml',20,16,15,15,false)," +
                        "('Skincare','Eurax','Skin cream','100g',5.7,4.2,15,15,false)," +
                        "('Skincare','Eucerin','Skin relief cream','50ml',9,7,20,20,false)," +
                        "('Skincare','Eucerin','Face scrub','100ml',7.5,6,20,20,false)," +
                        "('Skincare','Dermalex','Psoriasis cream','150ml',30,25,10,10,false)," +
                        "('Skincare','Dermalex','Repair and Restore','100g',12,10,10,10,false)," +
                        "('Skincare','Dermalex','Eczema cream','100g',25,22.2,5,5,false)," +
                        "('Skincare','Cetaphil','Moisturising cream','50ml',10,7.6,20,20,false)," +
                        "('Skincare','Cetaphil','Exfoliating cleanser','180ml',12,10.1,20,20,false);");
                
                sProduct1.executeUpdate("INSERT INTO SHOP_PRODUCT(CATEGORY,BRAND,NAME,AMOUNT,SELL_PRICE,BUY_PRICE,QUANTITY,FULL_STOCK,LIMIT_OF_1) VALUES('Headaches and Pain Relief','Nurofen','Meltlets','16 caps',4,3.7,40,40,false)," +
                        "('Headaches and Pain Relief','Nurofen','Express','16 caps',4,3.5,30,30,false)," +
                        "('Headaches and Pain Relief','Nurofen','Max strength','32 caps',7,6.2,25,25,false)," +
                        "('Headaches and Pain Relief','Nurofen','Standard','16 caps',4,3.2,30,30,false)," +
                        "('Headaches and Pain Relief','Cuprofen ','Max strength','96 caps',11,9,20,20,true)," +
                        "('Headaches and Pain Relief','Solpadeine','Headache','16 caps',2,1.6,20,20,true)," +
                        "('Headaches and Pain Relief','Anadin','Extra','16 caps',2.3,2,30,30,true)," +
                        "('Headaches and Pain Relief','Anadin','Triple action','12 caps',2,1.9,30,30,true)," +
                        "('Headaches and Pain Relief','Anadin','Original','16 caps',1.8,1.5,30,30,true)," +
                        "('Headaches and Pain Relief','Disprin','Soluble','32 tablets',3.6,2.8,20,20,true);");
                
                sProduct1.executeUpdate("INSERT INTO SHOP_PRODUCT(CATEGORY,BRAND,NAME,AMOUNT,SELL_PRICE,BUY_PRICE,QUANTITY,FULL_STOCK,LIMIT_OF_1) VALUES('Digestion','Dioralyte','Blackcurrant','12 sachets',8,7.3,20,20,false)," +
                        "('Digestion','Dioralyte','Lemon','12 sachets',8,7.3,20,20,false)," +
                        "('Digestion','Gaviscon','Chewable','24 tablets',4.2,3.5,25,25,false)," +
                        "('Digestion','Senokot','Max','10 tablets',3,2.7,10,10,false)," +
                        "('Digestion','Gaviscon','Advance','300ml',10,8.1,10,10,false);");
                sProduct1.executeUpdate("INSERT INTO SHOP_PRODUCT(CATEGORY,BRAND,NAME,AMOUNT,SELL_PRICE,BUY_PRICE,QUANTITY,FULL_STOCK,LIMIT_OF_1) VALUES('Allergy','Benadryl','Relief','24 caps',9,7.1,20,20,false)," +
                        "('Allergy','Piriteze','tabs','7 tablets',3,2.3,20,20,false)," +
                        "('Allergy','Beconase','Relief','100 sprays',6,4,20,20,false);");
                
                sProduct1.executeUpdate("INSERT INTO SHOP_PRODUCT(CATEGORY,BRAND,NAME,AMOUNT,SELL_PRICE,BUY_PRICE,QUANTITY,FULL_STOCK,LIMIT_OF_1) VALUES('First Aid','Dettol','Antiseptic','500ml',3.2,3,20,20,false)," +
                        "('First Aid','Dettol','Hand sanitizer','500ml',7,6.3,50,50,false)," +
                        "('First Aid','Elastoplast','plasters','20 plasters',3,2,30,30,false)," +
                        "('First Aid','TCP','Liquid','200ml',4,3.2,20,20,false);");
                sProduct1.executeUpdate("UPDATE SHOP_PRODUCT SET BRANCH_ID = 1;");
                sProduct1.executeUpdate("INSERT INTO SHOP_PRODUCT (CATEGORY,BRAND,NAME,AMOUNT,SELL_PRICE,BUY_PRICE,QUANTITY,FULL_STOCK,LIMIT_OF_1) SELECT CATEGORY,BRAND,NAME,AMOUNT,SELL_PRICE,BUY_PRICE,QUANTITY,FULL_STOCK,LIMIT_OF_1 FROM SHOP_PRODUCT WHERE BRANCH_ID=1;");
                sProduct1.executeUpdate("UPDATE SHOP_PRODUCT SET BRANCH_ID=2 WHERE BARCODE>41;");
                sProduct1.executeUpdate("UPDATE SHOP_PRODUCT SET SELL_PRICE=SELL_PRICE*2 WHERE BARCODE>41;");
                sProduct1.executeUpdate("INSERT INTO SHOP_PRODUCT (CATEGORY,BRAND,NAME,AMOUNT,SELL_PRICE,BUY_PRICE,QUANTITY,FULL_STOCK,LIMIT_OF_1) SELECT CATEGORY,BRAND,NAME,AMOUNT,SELL_PRICE,BUY_PRICE,QUANTITY,FULL_STOCK,LIMIT_OF_1 FROM SHOP_PRODUCT WHERE BRANCH_ID=1;");
                sProduct1.executeUpdate("UPDATE SHOP_PRODUCT SET BRANCH_ID=3 WHERE BARCODE>82;");
                sProduct1.executeUpdate("UPDATE SHOP_PRODUCT SET SELL_PRICE=SELL_PRICE/1.3 WHERE BARCODE>82;");
            }

            else if(tableName.equals("order_product")){
                sBasket = c.createStatement();
                String sql ="CREATE TABLE ORDER_PRODUCT (" +
                        "BARCODE SERIAL PRIMARY KEY NOT NULL," +
                        "CATEGORY VARCHAR(36) NOT NULL," +
                        "BRAND VARCHAR(36) NOT NULL," +
                        "NAME VARCHAR(36) NOT NULL," +
                        "AMOUNT VARCHAR(36)," +
                        "SELL_PRICE DECIMAL(10,2) NOT NULL," +
                        "QUANTITY SMALLINT NOT NULL," +
                        "LIMIT_OF_1 BOOLEAN NOT NULL," +
                        "CUSTOMER_ID INT REFERENCES CUSTOMER (ID))";
                sBasket.executeUpdate(sql);
            }

            else if(tableName.equals("logged_in_customer")) { //this table is so that we can see which customer is currently logged in - there will only be at most 1 entry in this table, and will be updated when a new user logs in
                sLogged = c.createStatement();
                String sql ="CREATE TABLE LOGGED_IN_CUSTOMER (" +
                        "ID SERIAL PRIMARY KEY NOT NULL," +
                        "FIRST_NAME VARCHAR(36) NOT NULL," +
                        "LAST_NAME VARCHAR(36) NOT NULL," +
                        "EMAIL VARCHAR(256) NOT NULL," +
                        "PASSWORD VARCHAR(256) NOT NULL, " +
                        "POSTCODE VARCHAR(8) NOT NULL," +
                        "ADDRESS VARCHAR(128)," +
                        "PHONE_NO VARCHAR(12)," +
                        "CUSTOMER_ID INT REFERENCES CUSTOMER (ID))";
                sLogged.executeUpdate(sql);

            }
            else if(tableName.equals("card_details")) { //this table is so that we can see which customer is currently logged in - there will only be at most 1 entry in this table, and will be updated when a new user logs in
                String sql ="CREATE TABLE CARD_DETAILS " +
                        "(ID SERIAL PRIMARY KEY NOT NULL," +
                        " CARD_NO VARCHAR(16) NOT NULL, " +
                        " CVV VARCHAR(3) NOT NULL, " +
                        " SORT_CODE VARCHAR(6) NOT NULL, " +
                        " ACCOUNT_NO VARCHAR(8) NOT NULL, " +
                        " CUSTOMER_ID INT REFERENCES CUSTOMER (ID))";
                s.executeUpdate(sql);
                s1.executeUpdate("INSERT INTO CARD_DETAILS (CARD_NO,CVV,SORT_CODE,ACCOUNT_NO,CUSTOMER_ID) VALUES ('1111222233334444','435','401020','12345678',1);");
            }
            sProduct.close();
            sProduct1.close();
            sCustomer.close();
            sCustomer1.close();
            sBasket.close();
            sLogged.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
    }
    // Functions to execute queries, or amend to the database content //
    // Checking if user is logging in with an existing account
    public static boolean validateLogin(String email_in,String pass_in){
        boolean status = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            ps = c.prepareStatement("select * from customer where email=? and password=?;");
            ps.setString(1,email_in);
            ps.setString(2,pass_in);
            rs = ps.executeQuery();
            status = rs.next(); //Status is now true if an entry with the input email and password exists
            
            rs.close();
            ps.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
        return status;
    }

    // Checking if user is registering with an existing email address
    public static boolean validateRegister(String email_in){
        boolean status = false;
        Connection c = null;
        PreparedStatement ps = null;    
        ResultSet rs = null;
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(dbUrl);
            ps = c.prepareStatement("select * from customer where email=?;");
            ps.setString(1,email_in);
            rs = ps.executeQuery();
            status = rs.next(); //similar to line 121
            rs.close();
            ps.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
        return status;
    }

    //Looks for user entry with the specific email and password, then returns this as a User class (similar variables to the table)
    public static User getUser(String email_in, String pass_in){
        User u = new User();
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            PreparedStatement ps = c.prepareStatement("select * from customer where email=? and password=?;");
            ps.setString(1,email_in);
            ps.setString(2,pass_in);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                u.customer_id = rs.getInt("id");
                u.fname = rs.getString("first_name");
                u.lname = rs.getString("last_name");
                u.email = rs.getString("email");
                u.password = rs.getString("password");
                u.postcode = rs.getString("postcode");
                u.address = rs.getString("address");
                u.phoneno = rs.getString("phone_no");
            }
            rs.close();
            ps.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
        return u;
    }

    public static boolean nonNullField(String columnName){
        boolean status = false;
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select " + columnName + " from logged_in_customer;");
            status = rs.next(); //Status is now true if an entry with the email and password exists (i.e. the only entry in the table)
            rs.close();
            s.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
        return status;
    }
    public static String test(String columnName){
        String status = "test";
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select " + columnName + " from logged_in_customer;");
            while(rs.next()){
                status = rs.getString(1);
            } //Status is now true if an entry with the email and password exists (i.e. the only entry in the table)
            rs.close();
            s.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
        return status;
    }

    //If registration is valid, the customer's information will be added to the database
    public static void addUser(String fname_in,String lname_in, String email_in,String pass_in, String postcode_in, String address_in, String phoneno_in){
        Connection c = null;
        PreparedStatement ps = null;
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(dbUrl);
            ps = c.prepareStatement("insert into customer(first_name,last_name,email,password,postcode,address,phone_no) values(?,?,?,?,?,?,?);");
            ps.setString(1,fname_in);
            ps.setString(2,lname_in);
            ps.setString(3,email_in);
            ps.setString(4,pass_in);
            ps.setString(5,postcode_in);
            ps.setString(6,address_in);
            ps.setString(7,phoneno_in);
            ps.executeUpdate();
            
            ps.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
    }

    public static void updateCustomer(String tableName, String category, String value){
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        PreparedStatement ps = null;
        try{
            Class.forName("org.postgresql.Driver");
            Connection c  = DriverManager.getConnection(dbUrl);
            Statement s = c.createStatement();
            String sql = "select customer_id from logged_in_customer;";
            ResultSet rs = s.executeQuery(sql);
            int cust_id = 0;
            while(rs.next()){
                cust_id = rs.getInt("customer_id");
            }
            if (tableName.equals("customer")) {
                ps = c.prepareStatement("update customer set " + category + "=? where id=?;");
                ps.setString(1, value);
                ps.setInt(2, cust_id);
                ps.executeUpdate();
            }
            else if (tableName.equals("card_details")) {
                ps = c.prepareStatement("update card_details set " + category + "=?  where id=?;");
                ps.setString(1, value);
                ps.setInt(2, cust_id);
                ps.executeUpdate();
            }
            ps.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
    }

    //This function is to see who is currently logged into the website - in reality this probably won't work if multiple people are using the website at once
    public static User getCurrentUser(){
        User u = new User();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(dbUrl);
            s = c.createStatement();
            rs = s.executeQuery("select * from logged_in_customer;");
            while(rs.next()){
                u.customer_id = rs.getInt("customer_id");
                u.fname = rs.getString("first_name");
                u.lname = rs.getString("last_name");
                u.email = rs.getString("email");
                u.postcode = rs.getString("postcode");
                u.address = rs.getString("address");
                u.phoneno = rs.getString("phone_no");
            }

            rs.close();
            s.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
        return u;
    }
    //When a customer logs in/registers, it will update the table holding the info of the current user to their details
    public static void setLoggedInUser(User loggedInUser){
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s  = c.createStatement();
            s.executeUpdate("truncate table logged_in_customer;"); //instead of updating the table it will just empty it and add a new entry
            //String sql = "insert into logged_in_customer(first_name,last_name,email,password,postcode,customer_id) SELECT first_name,last_name,email,password,postcode,id FROM customer WHERE id=" +loggedInUser.customer_id +";";
            PreparedStatement ps = c.prepareStatement("insert into logged_in_customer(first_name,last_name,email,postcode,address,phone_no,customer_id) VALUES (?,?,?,?,?,?,?);");
            ps.setString(1,loggedInUser.fname);
            ps.setString(2,loggedInUser.lname);
            ps.setString(3,loggedInUser.email);
            ps.setString(4,loggedInUser.postcode);
            ps.setString(5,loggedInUser.address);
            ps.setString(6,loggedInUser.phoneno);
            ps.setInt(7,loggedInUser.customer_id);
            ps.executeUpdate();

            ps.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
    }
    //this is to see the customer is currently logged in
    public static boolean checkLoggedIn(){
        boolean status = false;
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(dbUrl);
            s = c.createStatement();
            rs = s.executeQuery("select * from logged_in_customer;");
            status = rs.next(); //Status is now true if an entry with the email and password exists (i.e. the only entry in the table)

            rs.close();
            s.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
        return status;
    }

    // Gets product attributes to display on browse page
    public static Product getProduct(int n){
        Product p = new Product();
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s = c.createStatement();;
            ResultSet rs = s.executeQuery("select * from shop_product where branch_id = 1 and barcode=" + n +";");
            while(rs.next()){
                p.barcode = rs.getInt("barcode");
                p.category = rs.getString("category");
                p.brand = rs.getString("brand");
                p.name = rs.getString("name");
                p.amount = rs.getString("amount");
                p.price = rs.getDouble("sell_price");
                p.quantity = rs.getInt("quantity");
                p.limited = rs.getBoolean("limit_of_1");
            }
            rs.close();
            s.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
        return p;
    }

    // Adds product to a basket table
    public static void addToBasket(Product p_in, int quantity_in){
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{ //if the customer adds Vicks vaporub x1 and then adds the same product but x2 again, we want it to display 'x2' rather than 'x1' and 'x2' appearing separately
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s = c.createStatement();
            String sql = "select customer_id from logged_in_customer;";
            ResultSet rs = s.executeQuery(sql);
            int cust_id = 0;
            while(rs.next()){
                cust_id = rs.getInt("customer_id");
            }

            String sql1 = "select * from ordered_product where name='" + p_in.name + "' and customer_id=" + cust_id + ";"; //We check if the user has previously added the item to the basket before
            Statement s1 = c.createStatement();
            ResultSet rs1 = s1.executeQuery(sql1);
            if(rs1.next()){ //If they have, we will update the quantity to the most recent value they have chosen (it won't add the amount on e.g. if they click x1 and then x3 it updates to x3, not x4 - this is for simplicity)
                Statement s2 = c.createStatement();
                String sql2 = "update ordered_product set quantity =" + quantity_in + " where name='" + p_in.name + "' and customer_id=" + cust_id + ";";
                s2.executeUpdate(sql2);
            }
            else { //If they haven't previously added the item to the basket, it will create a new entry in the table
                PreparedStatement ps = c.prepareStatement("insert into ordered_product (barcode,category,brand,name,amount,sell_price,quantity,limit_of_1,customer_id) values(?,?,?,?,?,?,?,?,?);");
                ps.setInt(1,p_in.barcode);
                ps.setString(2, p_in.category);
                ps.setString(3, p_in.brand);
                ps.setString(4, p_in.name);
                ps.setString(5, p_in.amount);
                ps.setDouble(6, p_in.price);
                ps.setInt(7, quantity_in); //Quantity added to basket rather than full stock quantity
                ps.setBoolean(8, p_in.limited);
                ps.setInt(9,cust_id);
                ps.executeUpdate();
                ps.close();
            }
            rs.close();
            rs1.close();
            s.close();
            s1.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
    }

    // Gets info from products added to basket to display on basket page, based on the position it appears on the page (line 301)
    public static Product getBasketInfo(int n){
        Product p = new Product();
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s = c.createStatement();
            String sql = "select customer_id from logged_in_customer;";
            ResultSet rs = s.executeQuery(sql);
            int cust_id = 0;
            while(rs.next()){
                cust_id = rs.getInt("customer_id");
            }
            PreparedStatement ps = c.prepareStatement("with temp as (select row_number() over (order by name asc) as rownum, * from ordered_product where customer_id=" + cust_id + ") select * from temp where rownum=?;");
            ps.setInt(1,n); // If one item is removed, the IDs aren't automatically updated e.g. if i remove item ID=2, table's ID will read as 1,3,4,5... this poses problems when using a for loop to display the information
            ResultSet rs1 = ps.executeQuery(); // SQL has no easy way to select item based on row number rather than an existing column - this is one solution
            while(rs1.next()){ // If int n = 2 (i.e. the second item in the basket), this will correspond to the 2nd entry in the basket table based on alphabetical order ('order by name asc' gives alphabetical order)
                p.barcode = rs1.getInt("barcode");
                p.brand = rs1.getString("brand");
                p.name = rs1.getString("name");
                p.amount = rs1.getString("amount");
                p.price = rs1.getDouble("sell_price");
                p.quantity = rs1.getInt("quantity");
                p.limited = rs1.getBoolean("limit_of_1");
            }
            rs.close();
            rs1.close();
            ps.close();
            s.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
        return p;
    }
    public static Double getBasketTotal(){
        double total = 0;
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s = c.createStatement();
            String sql = "select customer_id from logged_in_customer;";
            ResultSet rs = s.executeQuery(sql);
            int cust_id = 0;
            while(rs.next()){
                cust_id = rs.getInt("customer_id");
            }
            String sql1 = "select sum(sell_price*quantity) from ordered_product where customer_id=" + cust_id + ";"; //For some reason it won't return the existing value in the subtotal column
            Statement s1 = c.createStatement();
            ResultSet rs1 = s.executeQuery(sql1);
            while(rs1.next()){
                total = rs1.getDouble(1);
            }

            rs.close();
            rs1.close();
            s.close();
            s1.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
        return total;
    }
    // Removes item from the basket database when trash icon is pressed
    public static void removeFromBasket(int id_in){
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s = c.createStatement();
            String sql = "select customer_id from logged_in_customer;";
            ResultSet rs = s.executeQuery(sql);
            int cust_id = 0;
            while(rs.next()){
                cust_id = rs.getInt("customer_id");
            }
            Statement s1 = c.createStatement();
            String sql1 = "delete from ordered_product where customer_id=" + cust_id + " and barcode=" + id_in + ";"; //removes the item entry from the table
            s1.executeUpdate(sql1);

            rs.close();
            s.close();
            s1.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
    }
    public static CreditCard getCurrentCard(){
        CreditCard cc = new CreditCard();
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s = c.createStatement();
            String sql = "select customer_id from logged_in_customer;";
            ResultSet rs = s.executeQuery(sql);
            int cust_id = 0;
            while(rs.next()){
                cust_id = rs.getInt("customer_id");
            }
            PreparedStatement ps = c.prepareStatement("select * from card_details where customer_id=" + cust_id + ";");
            ResultSet rs1 = ps.executeQuery();
            while(rs1.next()){ // If int n = 2 (i.e. the second item in the basket), this will correspond to the 2nd entry in the basket table based on alphabetical order ('order by name asc' gives alphabetical order)
                cc.cardNumber = rs1.getString("card_no");
                cc.cvv = rs1.getString("cvv");
                cc.sortCode = rs1.getString("sort_code");
                cc.accountNumber = rs1.getString("account_no");
            }
            rs.close();
            rs1.close();
            ps.close();
            s.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
        return cc;

    }
    // Display size of table - i.e. number of entries - used as a for loop in displaying all the products in the browse page
    // Also useful when determining whether tables are empty

    public static int tableSize(String tableName){
        int n = 0; // Base case: n = 0 tells us the table is empty or something has gone wrong
        Statement s = null;
        ResultSet rs = null;
        String sql1 = null;
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            if (tableName.equals("ordered_product")) {
                s = c.createStatement();
                String sql = "select customer_id from logged_in_customer;";
                rs = s.executeQuery(sql);
                int cust_id = 0;
                while (rs.next()) {
                    cust_id = rs.getInt("customer_id");
                }
                sql1 = "select count(*) from ordered_product where customer_id=" + cust_id + ";"; // The SQL command 'select count(*)' gets the number of entries (i.e. the number of rows)
            }
            else if (tableName.equals("shop_product")){
                sql1 = "select count(*) from shop_product where branch_id=1;";
            }
            s = c.createStatement();
            rs = s.executeQuery(sql1);
            while(rs.next()){
                String p = rs.getString(1); // The SQL command 'select count' returns a string value, not a number
                n = Integer.parseInt(p); // So we convert that string to an integer
            }
            rs.close();
            s.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
        return n;
    }
    // To return the number of items in the basket - to be displayed on the navigation bar header
    public static int getBasketSize(){
        int n = 0;
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement s = c.createStatement();
            String sql = "select customer_id from logged_in_customer;";
            ResultSet rs = s.executeQuery(sql);
            int cust_id = 0;
            if(!rs.next()){
                return 0;
            }
            else{
                cust_id = rs.getInt("customer_id");
            }

            String sql1 = "select sum(quantity) from ordered_product where customer_id=" + cust_id + ";";// Although similar to 'tableSize()', this counts the number of items, not just the number of different products
            s = c.createStatement(); // i.e. x3 vicks and x2 dettol is 5 items comprised of 2 different products - the basket displays 5
            rs = s.executeQuery(sql1);
            while(rs.next()){
                String p = rs.getString(1);
                n = Integer.parseInt(p);
            }
            rs.close();
            s.close();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName()+": " + e.getMessage());
        }
        return n;
    }

    public void placeOrder(){
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            Statement stmt = c.createStatement();
            //stmt.execute("INSERT INTO ordered_product (name,quantity,sell_price,orders_id) VALUES(b.name,b.quantity,b.price,u.id)");
            int n = LoginDAO.tableSize("ordered_product");
            for(int i=1;i<n+1;i++) {
                Product b = LoginDAO.getBasketInfo(i);
                UpdateQuantity update = new UpdateQuantity(b.name, b.brand, -b.quantity);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}