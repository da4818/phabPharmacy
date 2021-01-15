package Website.Functions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//This class just stores a list of customers to be sent to the client UI using JSON
public class Customers implements Serializable {
    List<Customer> customers;
    public Customers(){
        customers = new ArrayList<>();
    }
    public void addCustomers(Customer customer) {
        this.customers.add(customer);
    }
}
