package Controllers.Cars;

import CarShop.Models.Cars;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.restlet.resource.*;
import org.json.simple.*;

import java.util.List;


public class Finder extends ServerResource {

    @Get("txt")
    public String toString(){
        JSONObject object    = new JSONObject();
        JSONArray  objects   = new JSONArray();
        JSONParser parser    = new JSONParser();
        int        page;
        String     orderBy   = getAttribute("order_by");
        String     order     = getAttribute("order");
        String     model     = getAttribute("model");
        String     maxPrice  = getAttribute("max_price");
        String     min_price = getAttribute("min_price");
        Cars       car;
        List       cars;

        try{
            page = Integer.parseInt( getAttribute("page") );
        }
        catch(NumberFormatException e){
            page = 0;
        }

        long ids[] = new long[2];

        ids[0] = 0;
        ids[1] = 1;

        cars = Cars.findCars(ids, ids, ids, 0, 1000, 0, 1000, 2000, 2016);

        for(int i=0; i<cars.size(); i++) {
            try {
                car = (Cars)cars.get(i);
                object = (JSONObject) parser.parse(car.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            objects.add(object);
        }

        return objects.toJSONString();
    }

}
