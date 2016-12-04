package Urls;

import org.restlet.routing.VirtualHost;


public class Urls {

    public static void initRoutes(VirtualHost host){
        host.attach("/login",                               Controllers.Users.Logger.class);
        host.attach("/register",                            Controllers.Users.Adder.class);
        host.attach("/add_administrator",                   Controllers.Users.Administrator.class);
        host.attach("/get_user",                            Controllers.Users.Getter.class);
        host.attach("/logout",                              Controllers.Users.Outer.class);
        host.attach("/find_cars",                           Controllers.Cars.Finder.class);
        host.attach("/get_car/{car_id}",                    Controllers.Cars.Getter.class);
        host.attach("/add_car",                             Controllers.Cars.Adder.class);
        host.attach("/get_cart",                            Controllers.Cart.Getter.class);
        host.attach("/add_product_to_cart",                 Controllers.Cart.Adder.class);
        host.attach("/remove_product_from_cart/{order_id}", Controllers.Cart.Deleter.class);
        host.attach("/confirm_purchase/{car_id}",           Controllers.Cart.PurchaseConfirmer.class);
        host.attach("/get_brands",                          Controllers.Brands.Getter.class);
        host.attach("/get_models/{brand}",                  Controllers.Models.Getter.class);
        host.attach("/get_colors",                          Controllers.Colors.Getter.class);
        host.attach("/get_orders",                          Controllers.Orders.Getter.class);
    }

}