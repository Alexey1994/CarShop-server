package CarShop.Models;


import org.junit.Test;

public class OrdersTest {
    @Test
    public void testSave(){
        Orders order = new Orders();

        order.save();
    }
}
