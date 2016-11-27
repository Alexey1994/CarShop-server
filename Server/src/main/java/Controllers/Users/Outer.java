package Controllers.Users;

import CarShop.Models.DAO.UsersDAO;
import Security.Coder;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.net.URLEncoder;


public class Outer extends ServerResource {
    @Get("txt")

    public String toString(){
        UsersDAO user = AuthenticatorBySession.getAuthenticateUser( this.getRequest().getCookies() );

        if(user == null)
            return "{\"status\":\"User not found\"}";

        user.setLastSession( URLEncoder.encode( Coder.getUniqueID() ) );
        user.save();

        return "{\"status\":\"Ok\"}";
    }
}
