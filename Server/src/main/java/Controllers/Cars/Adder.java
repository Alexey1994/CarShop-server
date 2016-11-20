package Controllers.Cars;

import CarShop.Models.CarBrandsDAO;
import CarShop.Models.CarModelsDAO;
import CarShop.Models.CarsDAO;
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

        CarsDAO car = new Cars(brand.getId(), 1, 1, 100, 200, 1000, 2014);

        return "{\"status\":\"Ok\"}";
    }
}