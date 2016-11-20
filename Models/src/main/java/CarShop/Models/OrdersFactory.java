package CarShop.Models;


import CarShop.Models.DAO.OrdersDAO;
import CarShop.Models.Implementation.Orders;

public class OrdersFactory {
    public static OrdersDAO getDAO(){
        return new Orders();
    }
}
