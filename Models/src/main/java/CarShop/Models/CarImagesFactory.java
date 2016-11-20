package CarShop.Models;


import CarShop.Models.DAO.CarImagesDAO;
import CarShop.Models.Implementation.CarImages;

public class CarImagesFactory {
    public static CarImagesDAO getDAO(){
        return new CarImages();
    }

    public static CarImagesDAO getDAO(long carId, String image){
        return new CarImages(carId, image);
    }
}
