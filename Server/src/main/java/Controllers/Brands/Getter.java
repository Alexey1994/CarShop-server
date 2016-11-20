package Controllers.Brands;

import CarShop.Models.CarBrandsFactory;
import CarShop.Models.DAO.CarBrandsDAO;
import CarShop.Models.Implementation.CarBrands;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.List;


public class Getter extends ServerResource {
    @Get("txt")
    public String toString(){
        List         brands       = CarBrandsFactory.getDAO().getAll();
        String       resultBrands = "[";

        for(int i=0; i<brands.size() - 1; i++)
            resultBrands += "\"" + ((CarBrandsDAO) brands.get(i)).toString() + "\",";

        if(brands.size() > 0)
            resultBrands += "\"" + ((CarBrandsDAO) brands.get(brands.size()-1)).toString() + "\"";

        resultBrands += "]";

        return resultBrands;
    }
}
