package CarShop.Models;

import org.hibernate.*;
import org.hibernate.Query;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
public class Sales {
    private static volatile long nextId = 0;

    @Id
    private long id;
    private long order_id;


    private static long generateId() {
        if(nextId != 0)
        {
            nextId++;
            return nextId;
        }

        Session session = DataBase.getSession();
        Query query = session.createQuery("SELECT id FROM Sales");
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
    public void setOrderId(long orderId){ this.order_id = orderId; }
    public long getOrderId(){ return this.order_id; }


    public Sales(){
        this.id = generateId();
    }


    public Sales(long orderId){
        this.id       = generateId();
        this.order_id = orderId;
    }


    public String toString(){
        return  "{" +
                "\"order_id\":" + this.order_id +
                "}";
    }
}
