package CarShop.Models.Implementation;

import CarShop.Models.DAO.UsersDAO;
import CarShop.Models.RolesFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class Users implements UsersDAO{
    @Id
    private long   id;
    private long   role_id;
    private String login;
    private String password;
    private String last_session;


    private long generateId(){
        return new Date().getTime();
    }


    public void save(){
        Session session         = DataBase.getSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        session.saveOrUpdate(this);
        transaction.commit();
        session.close();
    }


    public UsersDAO getUser(String login){
        Session     session     = DataBase.getSession();
        Transaction transaction = session.getTransaction();
        Query       query;
        List        users;
        UsersDAO    user;

        transaction.begin();

        query = session.createQuery("FROM Users WHERE login=:userLogin");
        query.setParameter("userLogin", login);
        users = query.getResultList();

        if(users.size() > 0)
            user = (UsersDAO) users.get(0);
        else
            user = null;

        transaction.commit();
        session.close();

        return user;
    }


    public List getAllAdmins(){
        Session     session     = DataBase.getSession();
        Transaction transaction = session.getTransaction();
        List        users;

        transaction.begin();

        users = session.createQuery("FROM Users WHERE role_id=1").list();

        transaction.commit();
        session.close();

        return users;
    }


    public long getId(){ return this.id; }
    public void setLogin(String login){ this.login = login; }
    public String getLogin(){ return this.login; }
    public void setPassword(String password){ this.password = password; }
    public String getPassword(){ return this.password; }
    public void setRoleId(long roleId){ this.role_id = roleId; }
    public long getRoleId(){ return this.role_id; }
    public void setLastSession(String session){ this.last_session = session; }
    public String getLastSession(){ return this.last_session; }


    public Users(String login, String password, long roleId, String lastSession){
        this.id           = generateId();
        this.login        = login;
        this.password     = password;
        this.role_id      = roleId;
        this.last_session = lastSession;
    }


    public Users(){}
}
