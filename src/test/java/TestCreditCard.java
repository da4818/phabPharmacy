import Website.Entities.CreditCard;
import Website.Entities.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Test; //Note that import org.junit.Test; will not work

public class TestCreditCard {
    @Test
    public void testCreditCardInput(){
        Customer c = new Customer("John", "Doe","SW72AZ","email@gmail.com",null,null);
        CreditCard ci = new CreditCard("1234 5678 1234 5678","123","123456","12345678", c);
        Assert.assertEquals(ci.cardNumber,"1234567812345678");
        CreditCard ci1 = new CreditCard("1234567812345678","123","123456","12345678", c);
        Assert.assertEquals(ci.cardNumber,"1234567812345678");
    }
    @Test
    public void testCreditCardOutput(){
        Customer c = new Customer("John", "Doe","SW72AZ","email@gmail.com",null,null);
        CreditCard ci = new CreditCard("1234567812345678","123","123456","12345678", c);
        Assert.assertEquals(ci.getCensoredCardNumber(),"123456781234xxxx");
    }
    @Test
    public void testSortCodeInput(){
        Customer c = new Customer("John", "Doe","SW72AZ","email@gmail.com",null,null);
        CreditCard ci1 = new CreditCard("1234567812345678","123","12-34-56","12345678", c);
        Assert.assertEquals(ci1.sortCode,"123456");
    }
    @Test
    public void testSortCodeOutput(){
        Customer c = new Customer("John", "Doe","SW72AZ","email@gmail.com",null,null);
        CreditCard ci = new CreditCard("1234567812345678","123","123456","12345678", c);
        Assert.assertEquals(ci.getSortCode(),"12-34-56");
    }

}
