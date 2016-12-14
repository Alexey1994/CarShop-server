package Controllers.Cars;

import CarShop.Models.CarsFactory;
import CarShop.Models.DAO.CarsDAO;
import CarShop.Models.DAO.UsersDAO;
import CarShop.Models.UsersFactory;
import Controllers.Users.AuthenticatorBySession;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.io.IOException;


public class Deleter extends ServerResource {
    @Post("txt")
    public String toString(Representation params) throws IOException {
        UsersDAO user = AuthenticatorBySession.getAuthenticatedUser(this.getRequest().getCookies());

        if(user == null || user.getRoleId() != 1)
            return "{\"status\":\"Error\"}";

        String in     = params.getText();
        long   carId;

        try {
            carId = Long.parseLong(in);
        } catch (NumberFormatException e){
            return "{\"status\":\"Error\"}";
        }

        CarsDAO car = CarsFactory.getDAO().get(carId);

        if(car == null)
            return "{\"status\":\"Error\"}";

        car.delete();

        return "{\"status\":\"Ok\"}";
    }
}
