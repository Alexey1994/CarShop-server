package CarShop.Models;

import CarShop.Models.DAO.UsersDAO;
import CarShop.Models.Implementation.Users;


public class UsersFactory {
    public static UsersDAO getDAO(){
        return new Users();
    }
}
