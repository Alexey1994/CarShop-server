package Controllers.Cart;

import CarShop.Models.CarsFactory;
import CarShop.Models.CartFactory;
import CarShop.Models.DAO.CarsDAO;
import CarShop.Models.DAO.CartDAO;
import CarShop.Models.DAO.UsersDAO;
import CarShop.Models.UsersFactory;
import Controllers.Users.AuthenticatorBySession;
import Security.Coder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.restlet.data.CookieSetting;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;

import java.net.URLEncoder;
import java.util.List;


public class Getter extends ServerResource {

    private long customerId;


    private void getCustomerId(){
        UsersDAO user = AuthenticatorBySession.getAuthenticatedUser(this.getRequest().getCookies());

        if(user == null) {
            String sessionId = Coder.getUniqueID();
            String login     = Coder.getUniqueID();
            String password  = Coder.getUniqueID();

            while(UsersFactory.getDAO().getUser(login) != null)
                login = Coder.getUniqueID();

            UsersFactory.getDAO(login, password, 2, sessionId).save();

            Series<CookieSetting> cookie = this.getResponse().getCookieSettings();
            cookie.add( new CookieSetting(0, "session_id", URLEncoder.encode(sessionId)));
            cookie.add( new CookieSetting(0, "login", URLEncoder.encode(login)));

            user = UsersFactory.getDAO().getUser(login);
        }

        customerId = user.getId();
    }


    @Get("txt")
    public String toString(){
        StringBuilder result = new StringBuilder();
        List          orders;

        getCustomerId();
        orders = CartFactory.getDAO().getOrders(customerId);

        result.append("[");

        for(int i=0; i<orders.size() - 1; ++i){
            CartDAO cart = (CartDAO)orders.get(i);/*
            CarsDAO car  = CarsFactory.getDAO().get(cart.getCarId());

            result.append(car.toString());*/
            result.append(cart.toString());
            result.append(",");
        }

        if(orders.size() > 0){
            CartDAO cart = (CartDAO)orders.get( orders.size()-1 );/*
            CarsDAO car  = CarsFactory.getDAO().get(cart.getCarId());

            result.append(car.toString());*/
            result.append(cart.toString());
        }

        result.append("]");

        return result.toString();
    }
}
