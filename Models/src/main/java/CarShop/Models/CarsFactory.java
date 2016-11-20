package CarShop.Models;


import CarShop.Models.DAO.CarsDAO;
import CarShop.Models.Implementation.Cars;

public class CarsFactory {
    public static CarsDAO getDAO(){
        return new Cars();
    }

    public static CarsDAO getDAO(long brandId, long modelId, long colorId, long power, long speed, long price, long yearOfManufacture){
        return new Cars(brandId, modelId, colorId, power, speed, price, yearOfManufacture);
    }
}