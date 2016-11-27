package CarShop.Models.Implementation;

import CarShop.Models.DAO.RolesDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;


@Entity
public class Roles implements RolesDAO {
    @Id
    private long   id;
    private String role;


    public String get(long roleId){
        Session     session     = DataBase.getSession();
        Transaction transaction = session.getTransaction();
        Roles       role;

        transaction.begin();

        List list = session.createQuery("FROM Roles WHERE id=" + roleId).list();

        if(list.size() == 0)
            return null;

        role = (Roles) list.get(0);

        return role.getRole();
    }


    public String getRole(){return this.role; }
    public void setRole(String role){ this.role = role; }

    public Roles(){}
}
