package Controllers.Cart;

import CarShop.Models.CarsFactory;
import CarShop.Models.CartFactory;
import CarShop.Models.DAO.CarsDAO;
import CarShop.Models.DAO.UsersDAO;
import CarShop.Models.UsersFactory;
import Controllers.Users.AuthenticatorBySession;
import Security.Coder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.restlet.data.CookieSetting;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;


public class Adder extends ServerResource {

    private JSONObject in;

    private long carId;
    private long customerId;


    private boolean getCarId(){
        String carIdString;

        try{
            carIdString = (String)in.get("car_id");
            carId = Long.parseLong(carIdString);
        } catch (ClassCastException e){
            return false;
        } catch (NumberFormatException e){
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


    private void addToCart(){
        CartFactory.getDAO(customerId, carId).save();
    }


    @Post("txt")
    public String toString(Representation params){
        JSONParser parser     = new JSONParser();

        try {
            in = (JSONObject) parser.parse(URLDecoder.decode( params.getText() ));
        }catch (ParseException e){
            return "{\"status\":\"error\"}";
        } catch (IOException e){
            return "{\"status\":\"error\"}";
        } catch (ClassCastException e){
            return "{\"status\":\"error\"}";
        }

        if(!getCarId())
            return "{\"status\":\"Error\"}";

        getCustomerId();
        addToCart();

        return "{\"status\":\"Ok\"}";
    }
}
