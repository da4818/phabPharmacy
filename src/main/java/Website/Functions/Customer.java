package Website.Functions;

import java.io.Serializable;
//This class just stores information about customers which can be transferred to the client using JSON
public class Customer implements Serializable {
    String address;
    String email;
    String first_name;
    int id;
    String last_name;
    String phone_no;
    String postcode;
    public Customer(String address, String email, String first_name, int id, String last_name, String phone_no, String postcode){
        this.address = address;
        this.email = email;
        this.first_name = first_name;
        this.id = id;
        this.last_name = last_name;
        this.phone_no = phone_no;
        this.postcode = postcode;
    }


}
