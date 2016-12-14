package Controllers.Images;

import CarShop.Models.CarImagesFactory;
import CarShop.Models.DAO.CarImagesDAO;
import CarShop.Models.DAO.UsersDAO;
import Controllers.Users.AuthenticatorBySession;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.io.IOException;


public class Saver extends ServerResource {
    @Post("txt")
    public String toString(Representation params){
        String   imageData;
        UsersDAO user = AuthenticatorBySession.getAuthenticatedUser(this.getRequest().getCookies());

        if(user == null || user.getRoleId() != 1)
            return "{\"status\":\"Error\"}";

        try {
            imageData = params.getText();
        } catch (IOException e) {
            return "{\"status\":\"Error\"}";
        }

        CarImagesDAO image = CarImagesFactory.getDAO();

        return "{\"status\":\"Ok\"}";
    }
}
