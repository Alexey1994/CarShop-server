package CarShop.Models;


import CarShop.Models.DAO.OrdersDAO;
import CarShop.Models.Implementation.Orders;

import java.sql.Date;

public class OrdersFactory {
    public static OrdersDAO getDAO(){
        return new Orders();
    }

    public static OrdersDAO getDAO(long carId, long customerId, Date orderDate, long statusId){
        return new Orders(carId, customerId, orderDate, statusId);
    }
}
