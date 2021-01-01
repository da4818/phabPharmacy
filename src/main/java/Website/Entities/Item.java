package Website.Entities;

public class Item {
    protected String name;
    protected String description;
    protected Double price;
    protected Integer quantity;
    protected String category;
    protected Boolean limited;
    public Item(){

    }
    public Item(String name, String description, Double price, Integer quantity, String category, Boolean limited) {
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.description=description;
        this.category=category;
        this.limited=limited;
    }
    public void setItem(String name, String description, Double price, Integer quantity, String category, Boolean limited) {
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.description=description;
        this.category=category;
        this.limited=limited;
    }
}
