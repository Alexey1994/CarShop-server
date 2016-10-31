import org.restlet.routing.VirtualHost;


public class Urls {

    public static void initRoutes(VirtualHost host){
        host.attach("/login",    Controllers.Users.Logger.class);
        host.attach("/register", Controllers.Users.Adder.class);

        host.attach("/find_cars/{page}/{order_by}/{order}/{model}/{max_price}/{min_price}", Controllers.Cars.Finder.class);
        host.attach("/get_cart",                                                            Controllers.Cart.Getter.class);
        host.attach("/add_product_to_cart",                                                 Controllers.Cart.Adder.class);
        host.attach("/remove_product_from_cart",                                            Controllers.Cart.Deleter.class);
        host.attach("/confirm_purchase",                                                    Controllers.Cart.PurchaseConfirmer.class);
    }

}