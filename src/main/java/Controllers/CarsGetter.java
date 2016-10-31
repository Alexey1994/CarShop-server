package Controllers;

import org.restlet.resource.ServerResource;
import org.restlet.resource.Get;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class CarsGetter extends ServerResource {
    @Get("txt")
    public String toString(){
        JSONArray cars = new JSONArray();
        JSONObject car = new JSONObject();

        car.put("model", "Audi");
        car.put("date", "24.03.1994");

        cars.add(car);

        return cars.toJSONString();
    }
}
