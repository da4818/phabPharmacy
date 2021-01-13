package Website.Entities;

import Website.LoginDAO;

public class User {
    public int customer_id;
    public String fname;
    public String lname;
    public String email;
    public String password;
    public String postcode;
    public String address;
    public String phoneno;

    public char nonNullEntries(){
        char out = 'n'; // None are non-null
        if (!LoginDAO.emptyField("address") && !LoginDAO.emptyField("phone_no")){
            out = 'b'; // Both are non-null
        }
        else if (LoginDAO.emptyField("address") && !LoginDAO.emptyField("phone_no")){
            out = 'p'; // Phone number is non-null
        }
        else if (!LoginDAO.emptyField("address") && LoginDAO.emptyField("phone_no")){
            out = 'a'; // Address is non-null
        }
        return out;
    }
}
