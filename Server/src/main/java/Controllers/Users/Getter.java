package Controllers.Users;

import CarShop.Models.DAO.RolesDAO;
import CarShop.Models.DAO.UsersDAO;
import CarShop.Models.RolesFactory;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;


public class Getter extends ServerResource {
    @Get("txt")
    public String toString(){
        UsersDAO user = AuthenticatorBySession.getAuthenticatedUser( this.getRequest().getCookies() );

        if(user == null)
            return "{\"status\":\"User not found\"}";

        String userString = "{";

        userString += "\"name\":\"" + user.getLogin() + "\",";
        userString += "\"role\":\"" + RolesFactory.getDAO().get( user.getRoleId() ) + "\"";

        userString += "}";

        return userString;
    }
}
