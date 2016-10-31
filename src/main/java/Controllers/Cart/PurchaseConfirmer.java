package Controllers.Cart;

import org.json.simple.JSONObject;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;


public class PurchaseConfirmer extends ServerResource {
    @Get("txt")
    public String toString(){
        JSONObject status = new JSONObject();

        status.put("status", "Ok");

        return status.toJSONString();
    }
}
