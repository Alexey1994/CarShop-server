package Controllers.Users;

import CarShop.Models.DAO.UsersDAO;
import CarShop.Models.UsersFactory;
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
import java.util.List;


public class Administrator extends ServerResource {

    JSONObject in;

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


    private void registerUser(){
        String   sessionId = Coder.getUniqueID();

        UsersFactory.getDAO(login, password, 1, sessionId).save();

        Series<CookieSetting> cookie = this.getResponse().getCookieSettings();
        cookie.add( new CookieSetting(0, "session_id", URLEncoder.encode(sessionId)));
        cookie.add( new CookieSetting(0, "login", URLEncoder.encode(login)));
    }


    private boolean loginBySession(){
        UsersDAO user = AuthenticatorBySession.getAuthenticatedUser(this.getRequest().getCookies());

        if(user == null)
            return false;

        if(user.getRoleId() != 1)
            return false;

        registerUser();

        return true;
    }


    private boolean registerFirstAdmin(){
        List admins = UsersFactory.getDAO().getAllAdmins();

        if(admins.size() > 0)
            return false;

        registerUser();

        return true;
    }

    @Post("txt")
    public String toString(Representation params){
        JSONParser parser    = new JSONParser();

        try {
            in = (JSONObject) parser.parse( URLDecoder.decode( params.getText(), "UTF-8" ) );
        } catch (ParseException e){
            return "{\"status\":\"Error\"}";
        } catch (ClassCastException e){
            return "{\"status\":\"Error\"}";
        } catch (IOException e){
            return "{\"status\":\"Error\"}";
        }

        if(!getLogin() || !getPassword())
            return "{\"status\":\"Error\"}";

        if(loginBySession())
            return "{\"status\":\"Ok\"}";

        if(!registerFirstAdmin())
            return "{\"status\":\"First administrator was registration\"}";

        return "{\"status\":\"Ok\"}";
    }

}
