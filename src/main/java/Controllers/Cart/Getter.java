package Controllers.Cart;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;


public class Getter extends ServerResource {
    @Get("txt")
    public String toString(){
        JSONObject car  = new JSONObject();
        JSONArray  cars = new JSONArray();

        return cars.toJSONString();
    }
}
