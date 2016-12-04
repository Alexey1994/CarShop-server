package Controllers.Cart;

import CarShop.Models.CarsFactory;
import CarShop.Models.DAO.CarsDAO;
import CarShop.Models.DAO.OrdersDAO;
import CarShop.Models.DAO.UsersDAO;
import CarShop.Models.OrdersFactory;
import CarShop.Models.UsersFactory;
import Controllers.Users.AuthenticatorBySession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Date;


public class PurchaseConfirmer extends ServerResource {

    private JSONObject in;

    private long carId;
    private long customerId;
    private Date orderDate;


    private boolean getCarId(){
        String stringCarId = getAttribute("car_id");

        if(stringCarId == null)
            return false;

        try{
            carId = Long.parseLong(stringCarId);
        } catch (ClassCastException e){
            return false;
        }

        CarsDAO car = CarsFactory.getDAO().get(carId);

        if(car == null)
            return false;

        return true;
    }


    private void getCustomerId(){
        UsersDAO user = AuthenticatorBySession.getAuthenticatedUser(this.getRequest().getCookies());

        if(user == null) {
            user = UsersFactory.getDAO().getUser("");
        }

        customerId = user.getId();
    }


    private void getOrderDate(){
        orderDate = new Date(new java.util.Date().getTime());
    }


    private void addOrder(){
        OrdersDAO order = OrdersFactory.getDAO(carId, customerId, orderDate, 1);
        order.save();
    }


    @Get("txt")
    public String toString(){
        if(!getCarId())
            return "{\"status\":\"Error\"}";

        getCustomerId();
        getOrderDate();
        addOrder();

        return "{\"status\":\"Ok\"}";
    }
}
