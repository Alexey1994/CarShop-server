package Controllers.Users;

import CarShop.Models.DAO.UsersDAO;
import CarShop.Models.UsersFactory;
import Security.Coder;
import org.hibernate.SessionBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.restlet.Response;
import org.restlet.data.CookieSetting;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class Adder extends ServerResource {
    private JSONObject in;

    private String login;
    private String password;


    private boolean getLogin(){
        try {
            login = (String)in.get("login");
        } catch (ClassCastException e){
            return false;
        }

        return true;
    }


    private boolean getPassword(){
        try {
            password = (String)in.get("password");
        } catch (ClassCastException e){
            return false;
        }

        password = Coder.encode(password);

        return true;
    }


    private boolean registrate(){
        UsersDAO user = UsersFactory.getDAO().getUser(login);

        if(user != null)
            return false;

        String   sessionId = Coder.getUniqueID();

        UsersFactory.getDAO(login, password, 0, sessionId).save();

        Series<CookieSetting> cookie = this.getResponse().getCookieSettings();
        cookie.add( new CookieSetting(0, "session_id", URLEncoder.encode(sessionId)));
        cookie.add( new CookieSetting(0, "login", URLEncoder.encode(login)));

        return true;
    }


    @Post("txt")
    public String toString(Representation params) throws IOException {
        JSONParser parser    = new JSONParser();

        try {
            in = (JSONObject) parser.parse( URLDecoder.decode( params.getText(), "UTF-8" ) );
        } catch (ParseException e){
            return "{\"status\":\"Error\"}";
        } catch (ClassCastException e){
            return "{\"status\":\"Error\"}";
        }

        if(!getLogin() || !getPassword())
            return "{\"status\":\"Error\"}";

        if(!registrate())
            return "{\"status\":\"User is exist\"}";

        return "{\"status\":\"Ok\"}";
    }
}
