package CarShop.Models;


import CarShop.Models.CartFactory;
import org.junit.Test;

public class CartTest {
    @Test
    public void testSave(){
        CartFactory.getDAO().save();
    }
}
