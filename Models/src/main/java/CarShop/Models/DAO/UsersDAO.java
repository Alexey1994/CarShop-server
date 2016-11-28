package CarShop.Models.DAO;

import java.util.List;


public interface UsersDAO {
    public void save();
    public UsersDAO getUser(String login);
    public List getAllAdmins();
    public void setLogin(String login);
    public String getLogin();
    public void setPassword(String password);
    public String getPassword();
    public void setRoleId(long roleId);
    public long getRoleId();
    public void setLastSession(String session);
    public String getLastSession();
}
