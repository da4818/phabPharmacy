package Website.Functions;

import java.io.Serializable;
import java.util.List;
//This class stores a list of products which represent an online customer's order
public class Order implements Serializable {
    List<Product> products;
    int customer;
    public Order(List<Product> products, int customer){
        this.products = products;
        this.customer = customer;
    }
    public List<Product> getProducts() {
        return products;
    }
    public int getCustomer() {
        return customer;
    }
}
