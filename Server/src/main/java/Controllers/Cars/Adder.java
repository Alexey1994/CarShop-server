package Controllers.Cars;

import CarShop.Models.DAO.CarBrandsDAO;
import CarShop.Models.DAO.CarModelsDAO;
import CarShop.Models.DAO.CarsDAO;
import CarShop.Models.Implementation.CarBrands;
import CarShop.Models.Implementation.CarModels;
import CarShop.Models.Implementation.Cars;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.restlet.representation.Representation;
import org.restlet.resource.*;

import java.io.IOException;


public class Adder extends ServerResource {

    @Post("txt")
    public String toString(Representation params) throws IOException {
        JSONParser   parser     = new JSONParser();
        JSONObject   in;
        String       paramBrand;
        String       paramModel;
        CarBrandsDAO brand;
        CarModelsDAO model;

        try {
            in = (JSONObject) parser.parse(params.getText());
        }catch (ParseException e){
            return "{\"status\":\"error\"}";
        }

        paramBrand = (String)in.get((Object)"brand");
        brand = new CarBrands().get(paramBrand);

        if(brand == null){
            brand = new CarBrands(paramBrand);
            brand.save();
        }

        paramModel = (String)in.get((Object)"model");
        model = new CarModels().get(paramModel, brand.getId());

        if(model == null){
            model = new CarModels(paramModel, brand.getId());
            model.save();
        }

        CarsDAO car = new Cars(brand.getId(), model.getId(), 1, 100, 100, 1000, 2014);

        car.save();

        return "{\"status\":\"Ok\"}";
    }
}
