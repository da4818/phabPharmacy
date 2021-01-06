import Website.Entities.CreditCard;
import org.junit.Assert;
import org.junit.jupiter.api.Test; //Note that import org.junit.Test; will not work

public class TestCreditCard {
    @Test
    public void testCreditCard(){
        CreditCard ci = new CreditCard("1234567812345678","123","12-34-56","12345678");
        Assert.assertEquals(ci.getCensoredCardNumber(),"123456781234xxxx");
        Assert.assertEquals(ci.getSortCode(),"123456");

        CreditCard ci1 = new CreditCard("1234 5678 1234 5678","123","12-34-56","12345678");
        Assert.assertEquals(ci1.getCardNumber(),"1234567812345678");
    }
}
