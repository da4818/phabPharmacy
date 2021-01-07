package Website.Entities;

public class Customer {
    public String first_name;
    public String last_name;
    public String postcode;
    public String email;
    public String address;
    public String phone_number;

    public Customer(String first_name, String last_name, String postcode, String email, String address, String phone_number){
        this.first_name = first_name;
        this.last_name = last_name;
        this.postcode = postcode;
        this.email = email;
        this.address = address;
        this.phone_number = phone_number;
    }

}