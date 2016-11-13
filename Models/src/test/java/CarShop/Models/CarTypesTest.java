package CarShop.Models;

import org.junit.Test;


public class CarTypesTest {
    @Test
    public void testSaveType(){
        CarTypes carType = new CarTypes("rs");

        carType.save();
    }
}
