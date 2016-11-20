package Controllers.Models;

import CarShop.Models.*;
import CarShop.Models.DAO.CarBrandsDAO;
import CarShop.Models.DAO.CarModelsDAO;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import java.util.List;


public class Getter extends ServerResource {
    @Get("txt")
    public String toString(){
        String       brandString = getAttribute("brand");
        CarBrandsDAO carBrand;
        CarModelsDAO carModel;
        String       modelsString;
        List         models;

        if(brandString == null)
            return "[]";

        carBrand = CarBrandsFactory.getDAO().get(brandString);

        if(carBrand == null)
            return "[]";

        models = CarModelsFactory.getDAO().getModelsWithBrand(carBrand.getId());

        modelsString = "[";

        for(int i=0; i<models.size()-1; ++i){
            carModel = (CarModelsDAO) models.get(i);
            modelsString += "\"" + carModel + "\",";
        }
        System.out.println(models.size());
        if(models.size()>0)
            modelsString += "\"" + models.get(models.size() - 1) + "\"";

        modelsString += "]";

        return modelsString;
    }
}
