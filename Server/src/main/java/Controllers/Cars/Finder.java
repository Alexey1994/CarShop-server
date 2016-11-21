package Controllers.Cars;

import CarShop.Models.*;
import CarShop.Models.DAO.CarBrandsDAO;
import CarShop.Models.DAO.CarModelsDAO;
import CarShop.Models.DAO.CarsDAO;
import CarShop.Models.Implementation.CarBrands;
import CarShop.Models.Implementation.Cars;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.restlet.representation.Representation;
import org.restlet.resource.*;
import org.json.simple.*;

import java.io.IOException;
import java.util.List;


public class Finder extends ServerResource {
    private final int numPagesOnPage = 5;

    @Post("txt")
    public String toString(Representation params) throws IOException {
        JSONParser parser = new JSONParser();
        JSONObject in;
        String     paramsString = params.getText();
        System.out.println(paramsString);
        try{
            in = (JSONObject) parser.parse(paramsString);
        }catch(ParseException e){
            return "{}";
        }

        JSONObject object       = new JSONObject();
        JSONObject result       = new JSONObject();
        JSONArray  objects      = new JSONArray();

        long       page;
        long       minPrice;
        long       maxPrice;
        String     orderBy      = (String)in.get("order_by");
        String     order        = (String)in.get("order");
        String     model        = (String)in.get("model");
        JSONArray  selectedCars;

        try{
            selectedCars = (JSONArray) in.get("selected_cars");
        }catch(ClassCastException e){
            return "{}";
        }

        CarsDAO    car;
        List       cars;

        try{
            minPrice = Long.parseLong((String)in.get("price_begin"));
        } catch (NumberFormatException e){
            minPrice = 0;
        }

        try{
            maxPrice = Long.parseLong((String)in.get("price_end"));
        } catch (NumberFormatException e){
            maxPrice = 999999999;
        }

        try{
            page = Long.parseLong((String)in.get("current_page")) - 1;
        } catch (NumberFormatException e){
            page = 0;
        }

        if(selectedCars == null)
            return "{}";

        long brandIds[] = new long[selectedCars.size()];
        long modelIds[] = new long[selectedCars.size()];

        for(int i=0; i<selectedCars.size(); i++){
            JSONObject   selectedCar = (JSONObject) selectedCars.get(i);
            String       carString = (String) selectedCar.get("type_car");
            String       modelString = (String) selectedCar.get("model_car");
            CarBrandsDAO brand;
            CarModelsDAO curModel;

            if(carString == null)
                return "{}";

            brand = CarBrandsFactory.getDAO().get(carString);

            if(brand == null)
                return "{}";

            brandIds[i] = brand.getId();

            System.out.println(brand.getId());

            if(modelString == null)
                return "{}";

            curModel = CarModelsFactory.getDAO().get(modelString, brand.getId());

            if(curModel == null) {
                curModel = CarModelsFactory.getDAO().get(0);
                System.out.println(curModel);
                System.out.println(modelString);

                if(modelString.compareTo(curModel.toString()) != 0)
                    return "{}";
            }

            modelIds[i] = curModel.getId();
            System.out.println(curModel.getId());
        }

        long ids[] = new long[1];

        ids[0] = 1;

        for(int i=0; i<brandIds.length; i++)
            if(brandIds[i] == 0) {
                brandIds = new long[0];
                modelIds = new long[0];
            }

        cars = new Cars().findCars(numPagesOnPage * 9999, brandIds, modelIds, ids, 0, 1000, 0, 1000, minPrice, maxPrice, 0, 2016);

        int startElement = (int)page * numPagesOnPage;

        for(int i=startElement; i<cars.size() && i < startElement + numPagesOnPage; i++) {
            car = (Cars)cars.get(i);

            try {
                object = (JSONObject) parser.parse(car.toString());
            }catch(ParseException e){
                return "{}";
            }

            objects.add(object);
        }

        result.put("result", objects);
        result.put("pages", cars.size() / numPagesOnPage);

        return result.toJSONString();
    }

}
