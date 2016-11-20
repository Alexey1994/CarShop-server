package CarShop.Models;


import CarShop.Models.DAO.SalesDAO;
import CarShop.Models.Implementation.Sales;

public class SalesFactory {
    public static SalesDAO getDAO(){
        return new Sales();
    }
}
