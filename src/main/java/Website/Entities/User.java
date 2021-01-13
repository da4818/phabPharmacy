package Website.Entities;

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
        if (!address.equals(null) && phoneno.equals(null)){
            out = 'b'; // Both are non-null
        }
        else if (!address.isEmpty() && phoneno.isEmpty()){
            out = 'p'; // Phone number is non-null
        }
        else if (address.isEmpty() && !phoneno.isEmpty()){
            out = 'a'; // Address is non-null
        }
        return out;
    }
}
