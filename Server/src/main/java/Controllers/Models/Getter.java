package Controllers.Models;

import CarShop.Models.CarBrandsDAO;
import CarShop.Models.CarModelsDAO;
import CarShop.Models.Implementation.CarBrands;
import CarShop.Models.Implementation.CarModels;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import java.util.List;


public class Getter extends ServerResource {
    @Get("txt")
    public String toString(){
        String       brandString = getAttribute("brand");
        CarBrandsDAO carBrands;
        List models;

        if(brandString == null)
            return "[]";

        carBrands = new CarBrands().get(brandString);

        if(carBrands == null)
            return "[]";

        models = new CarModels().
    }
}
