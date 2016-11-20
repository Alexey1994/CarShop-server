package CarShop.Models.Implementation;

import CarShop.Models.CarBrandsFactory;
import CarShop.Models.CarsFactory;
import CarShop.Models.DAO.CarBrandsDAO;
import CarShop.Models.DAO.CarsDAO;
import org.junit.Test;

import java.util.List;

public class CarBrandsTest {

    @Test
    public void testSaveCarModel(){
        CarBrandsDAO carBrand = CarBrandsFactory.getDAO("Acura");

        carBrand.save();
    }


    @Test
    public void testGetCarModel(){
        System.out.println(CarBrandsFactory.getDAO().get("Acura"));

        CarsDAO car      = CarsFactory.getDAO().get(1);
        CarBrandsDAO carModel = CarBrandsFactory.getDAO().get(car.getModelId());

        System.out.println(carModel);
    }


    @Test
    public void testGetAll(){
        List brands = CarBrandsFactory.getDAO().getAll();

        for(int i=0; i<brands.size(); i++)
            System.out.println(brands.get(i));
    }
}
