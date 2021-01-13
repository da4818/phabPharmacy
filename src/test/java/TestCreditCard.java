import Website.Entities.CreditCard;
import Website.Entities.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Test; //Note that import org.junit.Test; will not work

public class TestCreditCard {
    @Test
    public void testCreditCardInput(){
        // Spacing: users can input with or without spacing, and will be modified to a non-space format
        CreditCard cc = new CreditCard("1234 5678 1234 5678","123","123456","12345678");
        Assert.assertEquals(cc.cardNumber,"1234567812345678");
        // Spacing: if the user inputs a non-space fomat, nothing will change
        CreditCard cc1 = new CreditCard("1234567812345678","123","123456","12345678");
        Assert.assertEquals(cc1.cardNumber,"1234567812345678");
    }
    @Test
    public void testCreditCardOutput(){

        CreditCard cc = new CreditCard("1234567812345678","123","123456","12345678");
        Assert.assertEquals(cc.getCensoredCardNumber(),"123456781234xxxx");
    }
    @Test
    public void testSortCodeInput(){
        CreditCard cc = new CreditCard("1234567812345678","123","12-34-56","12345678");
        Assert.assertEquals(cc.sortCode,"123456");
    }
    @Test
    public void testSortCodeOutput(){
        CreditCard cc = new CreditCard("1234567812345678","123","123456","12345678");
        Assert.assertEquals(cc.getSortCode(),"12-34-56");
    }

}
