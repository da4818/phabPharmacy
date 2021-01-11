import Website.Entities.Address;
import org.junit.Assert;
import org.junit.jupiter.api.Test; //Note that import org.junit.Test; will not work
public class testAddress {
    @Test
    public void testPostcode(){
        Address ad = new Address("Exhibition Road\nKensington\nLondon", "SW7 2az");
        System.out.println(ad.address);
        Assert.assertEquals(ad.postcode,"SW7 2AZ");
        Assert.assertEquals(ad.validPostcode(),true);
    }
}
