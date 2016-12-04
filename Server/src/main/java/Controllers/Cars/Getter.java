package Controllers.Cars;

import CarShop.Models.CarsFactory;
import CarShop.Models.DAO.CarsDAO;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;


public class Getter extends ServerResource {
    @Get("txt")
    public String toString(){
        long carId;

        try {
            carId = Long.parseLong(getAttribute("car_id"));
        } catch(NumberFormatException e){
            return "{}";
        }

        CarsDAO car = CarsFactory.getDAO().get(carId);

        if(car == null)
            return "{}";

        return car.toString();
    }
}
