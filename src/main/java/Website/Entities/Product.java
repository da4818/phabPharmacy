package Website.Entities;


public class Product {
    public int barcode; // is this the equivalent to the barcode?
    public String category;
    public String brand;
    public String name;
    public String amount;
    public Double price;
    public Integer quantity;
    public Boolean limited;

    public int change;
    public String saleLimit;

    public Product(){ //useful in getProduct function

    }
    public Product(String name,String brand, int change){
        this.name = name;
        this.brand = brand;
        this.change = change; //what is change?
    }
    public Product(String category, String brand, String name, String amount,Double price, boolean limited) {
        this.category = category;
        this.brand = brand;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.limited = limited;
        //this.saleLimit = saleLimit; //what is saleLimit?


    }

}
