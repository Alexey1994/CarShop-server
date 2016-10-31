import org.restlet.routing.VirtualHost;


public class Urls {

    public static void initRoutes(VirtualHost host){
        host.attach("/get_cars", Controllers.CarsGetter.class);
    }

}