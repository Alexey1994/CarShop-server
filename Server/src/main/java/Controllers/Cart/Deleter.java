package Controllers.Cart;

import CarShop.Models.CartFactory;
import CarShop.Models.DAO.CartDAO;
import org.json.simple.JSONObject;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;


public class Deleter extends ServerResource {
    @Get("txt")
    public String toString(){
        String stringOrderId = getAttribute("order_id");
        long   orderId;

        try{
            orderId = Long.parseLong(stringOrderId);
        } catch (NumberFormatException e){
            return "{\"status\":\"error\"}";
        }

        CartDAO cart = CartFactory.getDAO().get(orderId);

        if(cart == null)
            return "{\"status\":\"error\"}";

        cart.delete();

        return "{\"status\":\"Ok\"}";
    }
}
