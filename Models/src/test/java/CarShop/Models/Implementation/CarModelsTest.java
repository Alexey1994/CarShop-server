package CarShop.Models.Implementation;

import CarShop.Models.CarModelsFactory;
import CarShop.Models.DAO.CarModelsDAO;
import org.junit.Test;


public class CarModelsTest {
    @Test
    public void testSaveModel(){
        CarModelsDAO carModel = CarModelsFactory.getDAO("rs", 1);

        carModel.save();
    }


    @Test
    public void testGetModel(){
        CarModelsDAO carModel = CarModelsFactory.getDAO().get("RSX", 1);

        System.out.println(carModel);
    }
}
