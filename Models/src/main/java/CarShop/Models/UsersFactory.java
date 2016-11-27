package CarShop.Models;

import CarShop.Models.DAO.UsersDAO;
import CarShop.Models.Implementation.Users;


public class UsersFactory {
    public static UsersDAO getDAO(){
        return new Users();
    }

    public static UsersDAO getDAO(String login, String password, long roleId, String lastSession){
        return new Users(login, password, roleId, lastSession);
    }
}
