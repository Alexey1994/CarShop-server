package CarShop.Models.Implementation;

import CarShop.Models.DAO.CartDAO;
import org.hibernate.*;
import org.hibernate.Query;

import javax.persistence.*;
import java.util.List;


@Entity
public class Cart implements CartDAO {
    private static volatile long nextId = 0;

    @Id
    private long id;
    private long user_id;
    private long car_id;


    private static long generateId() {
        if(nextId != 0)
        {
            nextId++;
            return nextId;
        }

        Session session = DataBase.getSession();
        Query query = session.createQuery("SELECT id FROM Cart");
        List ids = query.list();
        Long maxId = null;

        if (ids.size() > 0)
            maxId = (Long) ids.get(ids.size() - 1);
        else
        {
            nextId++;
            return nextId;
        }
        System.out.println(maxId);
        nextId = maxId.longValue() + 1;

        return nextId;
    }


    public void save() {
        Session session = DataBase.getSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        session.saveOrUpdate(this);
        transaction.commit();
        session.close();
    }


    public long getId(){ return this.id; }
    public void setUserId(long userId){ this.user_id = userId; }
    public long getUserId(){ return this.user_id; }
    public void setcarId(long carId){ this.car_id = carId; }
    public long getCarId(){ return this.car_id; }


    public Cart(){
        this.id = generateId();
    }


    public Cart(long userId, long carId){
        this.id       = generateId();
        this.user_id  = userId;
        this.car_id   = carId;
    }


    public String toString(){
        return "{" +
                "\"user_id\":\"" + this.user_id + "\"," +
                "\"car_id\":\"" + this.car_id +
                "\"}";
    }
}
