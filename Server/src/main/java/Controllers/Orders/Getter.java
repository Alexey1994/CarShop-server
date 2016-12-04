package Controllers.Orders;

import CarShop.Models.DAO.OrdersDAO;
import CarShop.Models.DAO.UsersDAO;
import CarShop.Models.OrdersFactory;
import Controllers.Users.AuthenticatorBySession;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.List;


public class Getter extends ServerResource {
    @Get("txt")
    public String toString(){
        UsersDAO user = AuthenticatorBySession.getAuthenticatedUser(this.getRequest().getCookies());

        if(user == null || user.getRoleId() != 1)
            return "[]";

        List orders;

        StringBuilder result = new StringBuilder();
        orders = OrdersFactory.getDAO().getAll();

        result.append("[");

        for(int i=0; i<orders.size() - 1; ++i){
            result.append(orders.get(i).toString());
            result.append(",");
        }

        if(orders.size() > 0)
            result.append(orders.get(orders.size() - 1).toString());

        result.append("]");

        return result.toString();
    }
}
