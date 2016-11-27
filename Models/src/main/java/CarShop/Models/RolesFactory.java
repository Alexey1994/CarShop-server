package CarShop.Models;

import CarShop.Models.DAO.RolesDAO;
import CarShop.Models.Implementation.Roles;


public class RolesFactory {
    public static RolesDAO getDAO(){
        return new Roles();
    }
}
