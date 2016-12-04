package CarShop.Models;


import CarShop.Models.DAO.CartDAO;
import CarShop.Models.Implementation.Cart;

public class CartFactory {
    public static CartDAO getDAO(){
        return new Cart();
    }

    public static CartDAO getDAO(long customerId, long carId){
        return new Cart(customerId, carId);
    }
}
