package CarShop.Models;

import org.junit.Test;


public class ColorsTest {
    @Test
    public void testSaveColor(){
        Colors color = new Colors("red");

        color.save();
    }
}
