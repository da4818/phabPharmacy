import Website.Entities.Address;
import org.junit.Assert;
import org.junit.jupiter.api.Test; //Note that import org.junit.Test; will not work
public class TestAddress {
    @Test
    public void testPostcode(){
        // INVALID ENTRIES
        // Non alpha-numeric characters
        Address ad = new Address("","$W72AZ");
        Assert.assertEquals(ad.validPostcode(),false);
        // Invalid spacing
        Address ad1 = new Address("","S W72AZ");
        Assert.assertEquals(ad1.validPostcode(),false);
        Address ad1a = new Address("","SW 72AZ");
        Assert.assertEquals(ad1a.validPostcode(),false);
        Address ad1b = new Address("","SW72 AZ");
        Assert.assertEquals(ad1b.validPostcode(),false);
        Address ad1c = new Address("","SW72A Z");
        Assert.assertEquals(ad1c.validPostcode(),false);

        // Too long postcode
        Address ad2 = new Address("","SW723 2AZ");
        Assert.assertEquals(ad2.validPostcode(),false);
        // Too short postcode
        Address ad3 = new Address("","S 2AZ");


        Address adr = new Address("Exhibition Road\nKensington\nLondon", "SW7 2az");
        Assert.assertEquals(adr.displayAddress(1),"Exhibition Road<br>Kensington<br>London<br>");
        // VALID ENTRIES
        // Non case-sensitive: outputs to capitalised format
        Address ad4 = new Address("", "sw7 2az");
        Assert.assertEquals(ad4.postcode,"SW7 2AZ");
        Address ad4a = new Address("", "Sw7 2az");
        Assert.assertEquals(ad4a.postcode,"SW7 2AZ");
        Address ad4b = new Address("", "SW7 2az");
        Assert.assertEquals(ad4b.postcode,"SW7 2AZ");

        // Non space: outputs to xxx xxx format
        Address ad5 = new Address("","SW72AZ");
        Assert.assertEquals(ad5.postcode,"SW7 2AZ");

        // Non-space and non case-sensitive: outputs to capitalised xxx xxx format
        Address ad6 = new Address("","sW72aZ");
        Assert.assertEquals(ad6.postcode,"SW7 2AZ");
    }
}
