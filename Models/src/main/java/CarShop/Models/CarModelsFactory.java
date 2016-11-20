package CarShop.Models;

import CarShop.Models.DAO.CarModelsDAO;
import CarShop.Models.Implementation.CarModels;


public class CarModelsFactory {
    public static CarModelsDAO getDAO(){
        return new CarModels();
    }

    public static CarModelsDAO getDAO(String model, long brandId){
        return new CarModels(model, brandId);
    }
}
