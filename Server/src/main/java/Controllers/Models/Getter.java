package Controllers.Models;

import CarShop.Models.*;
import CarShop.Models.DAO.CarBrandsDAO;
import CarShop.Models.DAO.CarModelsDAO;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;


public class Getter extends ServerResource {
    @Get("txt")
    public String toString(){
        String       brandString;
        CarBrandsDAO carBrand;
        CarModelsDAO carModel;
        String       modelsString;
        List         models;

        try{
            brandString = URLDecoder.decode(getAttribute("brand"), "UTF-8");
        }catch(UnsupportedEncodingException e){
            brandString = null;
        }

        if(brandString == null)
            return "[]";

        carBrand = CarBrandsFactory.getDAO().get(brandString);

        if(carBrand == null)
            return "[]";

        models = CarModelsFactory.getDAO().getModelsWithBrand(carBrand.getId());

        modelsString = "[";

        if(carBrand.getId() != 0){
            carModel = CarModelsFactory.getDAO().get(0);

            if(models.size() > 0)
                modelsString += "\"" + carModel + "\",";
            else
                modelsString += "\"" + carModel + "\"";
        }

        for(int i=0; i<models.size()-1; ++i){
            carModel = (CarModelsDAO) models.get(i);
            modelsString += "\"" + carModel + "\",";
        }

        if(models.size()>0)
            modelsString += "\"" + models.get(models.size() - 1) + "\"";

        modelsString += "]";

        return modelsString;
    }
}
