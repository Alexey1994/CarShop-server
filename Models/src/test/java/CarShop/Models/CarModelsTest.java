package CarShop.Models;

import org.junit.Test;

public class CarModelsTest {

    @Test
    public void testSaveCarModel(){
        CarModels carModel = new CarModels("Audi");

        carModel.save();
    }
}
