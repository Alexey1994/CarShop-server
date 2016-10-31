import org.restlet.routing.VirtualHost;


public class Urls {

    public static void initRoutes(VirtualHost host){
        host.attach("/find_cars/{page}/{order_by}/{order}/{model}/{max_price}/{min_price}", Controllers.Cars.Finder.class);
    }

}