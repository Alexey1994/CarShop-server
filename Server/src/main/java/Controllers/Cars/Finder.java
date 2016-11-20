package Controllers.Cars;

import CarShop.Models.*;
import CarShop.Models.DAO.CarsDAO;
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
    public String toString(Representation params) throws IOException, ParseException {
        JSONParser parser     = new JSONParser();
        JSONObject in         = (JSONObject) parser.parse(params.getText());
        JSONObject object     = new JSONObject();
        JSONObject result     = new JSONObject();
        JSONArray  objects    = new JSONArray();

        long       page;
        long       minPrice;
        long       maxPrice;
        String     orderBy   = (String)in.get("order_by");
        String     order     = (String)in.get("order");
        String     model     = (String)in.get("model");

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


        long ids[] = new long[2];

        ids[0] = 0;
        ids[1] = 1;

        cars = new Cars().findCars(numPagesOnPage, ids, ids, ids, 0, 1000, 0, 1000, minPrice, maxPrice, 0, 2016);

        for(int i=(int)page * numPagesOnPage; i<cars.size(); i++) {
            car = (Cars)cars.get(i);
            object = (JSONObject) parser.parse(car.toString());
            objects.add(object);
        }

        result.put("result", objects);
        result.put("pages", cars.size() / (numPagesOnPage + 1));

        return result.toJSONString();
    }

}
