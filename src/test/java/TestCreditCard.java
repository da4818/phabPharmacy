import Website.Entities.CreditCard;
import Website.Entities.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Test; //Note that import org.junit.Test; will not work

public class TestCreditCard {
    @Test
    public void testCreditCard(){
        Customer c = new Customer("John", "Doe","SW72AZ","email@gmail.com",null,null);
        CreditCard ci = new CreditCard("1234567812345678","123","12-34-56","12345678", c);
        Assert.assertEquals(ci.getCensoredCardNumber(),"123456781234xxxx");
    }
}
