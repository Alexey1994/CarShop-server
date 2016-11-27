package Controllers.Users;

import CarShop.Models.DAO.UsersDAO;
import CarShop.Models.UsersFactory;
import org.restlet.data.Cookie;
import org.restlet.util.Series;

import java.net.URLDecoder;


public class AuthenticatorBySession {
    public static UsersDAO getAuthenticateUser(Series<Cookie> cookie){
        String login   = cookie.getValues("login");
        String session = cookie.getValues("session_id");

        if(login == null || session == null)
            return null;

        login = URLDecoder.decode(login);
        session = URLDecoder.decode(session);

        UsersDAO user = UsersFactory.getDAO().getUser(login);

        if(user == null)
            return null;

        if(user.getLastSession().compareTo( session ) != 0)
            return null;

        return user;
    }


    public static boolean isAuthenticate(Series<Cookie> cookie){
        return getAuthenticateUser(cookie) != null;
    }
}
