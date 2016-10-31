package Controllers.Cars;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Finder extends ServerResource {

    @Get("txt")
    public String toString(){
        JSONObject car       = new JSONObject();
        JSONArray  cars      = new JSONArray();
        int        page      = Integer.getInteger( getAttribute("page") );
        String     orderBy   = getAttribute("order_by");
        String     order     = getAttribute("order");
        String     model     = getAttribute("model");
        String     maxPrice  = getAttribute("max_price");
        String     min_price = getAttribute("min_price");

        car.put("model", "Audi");
        car.put("type", "sedan");

        cars.add(car);

        return cars.toJSONString();
    }

}
