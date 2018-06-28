package ebaydb.models;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class EbayItemPriceTest {

    private Date date = new Date();
    private EbayItem item = new EbayItem();

    @Test
    public void testEquals() {
        EbayItemPrice price1 = createPrice(), price2 = createPrice();

        assertEquals(price1, price2);

        price2 = createPrice();
        price2.setTimestamp(new Date(0));

        assertEquals(price1, price2);

        price2 = createPrice();
        price2.setPrice(0);

        assertNotEquals(price1, price2);
    }

    private EbayItemPrice createPrice() {
        EbayItemPrice price = new EbayItemPrice();
        price.setId(0L);
        price.setItem(item);
        price.setPrice(10);
        price.setShipping(0);
        price.setCurrency("EUR");
        price.setTimestamp(date);
        return price;
    }
}