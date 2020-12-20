import java.sql.*;
//DAO = data access object
public class LoginDAO {
    public static void createTable(){
        Statement stmt;
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(dbUrl);
            DatabaseMetaData dbmd= c.getMetaData();
            ResultSet rs = dbmd.getTypeInfo();
            if(rs.next()==false){
                stmt = c.createStatement();
                String sql ="CREATE TABLE USERINFO " +
                        "(ID INT PRIMARY KEY NOT NULL," +
                        " NAME TEXT NOT NULL, " +
                        " EMAIL TEXT NOT NULL, " +
                        " PASS TEXT NOT NULL)";
                stmt.executeUpdate(sql);
                Statement s1 = c.createStatement();
                String sql1 = "INSERT INTO USERINFO (ID,NAME,EMAIL,PASS) VALUES (1, 'Luke', 'email1', 'pass1');";
                String sql2 = "INSERT INTO USERINFO (ID,NAME,EMAIL,PASS) VALUES (2, 'Gina', 'email2', 'pass2');";
                s1.executeUpdate(sql1);
                Statement s2 = c.createStatement();
                s2.executeUpdate(sql2);
                stmt.close();
                s1.close();
                s2.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static boolean validate(String email,String pass){
        boolean status=false;
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(dbUrl);

            PreparedStatement ps=con.prepareStatement("select * from userinfo where email=? and pass=?");
            ps.setString(1,email);
            ps.setString(2,pass);

            ResultSet rs=ps.executeQuery();
            status=rs.next();

        }catch(Exception e){System.out.println(e);}
        return status;
    }
    public static String getName(String email,String pass){
        String userName="";
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            Class.forName("org.postgresql.Driver");
            Connection con=DriverManager.getConnection(dbUrl);

            PreparedStatement ps=con.prepareStatement("select * from userinfo where email=? and pass=?");
            ps.setString(1,email);
            ps.setString(2,pass);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                userName = rs.getString("name");
            }
        }catch(Exception e){System.out.println(e);}
        return userName;
    }
}

