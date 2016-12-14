package Controllers.Orders;

import CarShop.Models.CarBrandsFactory;
import CarShop.Models.CarModelsFactory;
import CarShop.Models.CarsFactory;
import CarShop.Models.DAO.*;
import CarShop.Models.OrdersFactory;
import Controllers.Users.AuthenticatorBySession;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import java.util.List;


public class HistoryGetter extends ServerResource {
    @Get("csv")
    public String toString(){
        UsersDAO user   = AuthenticatorBySession.getAuthenticatedUser(this.getRequest().getCookies());

        if(user == null)
            return "";

        List          orders  = OrdersFactory.getDAO().getAll(user.getId());
        StringBuilder history = new StringBuilder();

        for(int i=0; i<orders.size(); ++i){
            OrdersDAO order = (OrdersDAO)orders.get(i);
            CarsDAO car = CarsFactory.getDAO().get( order.getCarId() );

            if(car == null)
                return "";

            CarBrandsDAO brand = CarBrandsFactory.getDAO().get( car.getBrandId() );

            if(brand == null)
                return "";

            CarModelsDAO model = CarModelsFactory.getDAO().get(car.getModelId());

            if(model == null)
                return "";

            history.append(brand.toString());
            history.append(";");
            history.append(model.toString());
            history.append(";");
            history.append(car.getPrice());
            history.append(";");
            history.append(order.getOrderDate());
            history.append(";");

            history.append("\r\n");
        }

        return history.toString();
    }
}
