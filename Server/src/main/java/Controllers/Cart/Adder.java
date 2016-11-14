package Controllers.Cart;

import org.json.simple.JSONObject;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;


public class Adder extends ServerResource {
    @Post("txt")
    public String toString(){
        JSONObject status = new JSONObject();

        status.put("status", "Ok");

        return status.toJSONString();
    }
}
