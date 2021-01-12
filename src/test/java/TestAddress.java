import Website.Entities.Address;
import org.junit.Assert;
import org.junit.jupiter.api.Test; //Note that import org.junit.Test; will not work
public class TestAddress {
    @Test
    public void testPostcode(){
        Address ad = new Address("Exhibition Road\nKensington\nLondon", "SW7 2az");
        Assert.assertEquals(ad.postcode,"SW7 2AZ");
        Assert.assertEquals(ad.validPostcode(),true);
        Assert.assertEquals(ad.getAddress(),"Exhibition Road<br>Kensington<br>London<br>");
    }
}
