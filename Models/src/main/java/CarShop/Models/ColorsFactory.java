package CarShop.Models;

import CarShop.Models.DAO.ColorsDAO;
import CarShop.Models.Implementation.Colors;


public class ColorsFactory {
    public static ColorsDAO getDAO(){
        return new Colors();
    }

    public static ColorsDAO getDAO(String name){
        return new Colors(name);
    }
}
