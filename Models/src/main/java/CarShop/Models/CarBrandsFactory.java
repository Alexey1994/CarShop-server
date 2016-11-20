package CarShop.Models;

import CarShop.Models.DAO.CarBrandsDAO;
import CarShop.Models.Implementation.CarBrands;


public class CarBrandsFactory {
    public static CarBrandsDAO getDAO(){
        return new CarBrands();
    }

    public static CarBrandsDAO getDAO(String brand){
        return new CarBrands(brand);
    }
}
