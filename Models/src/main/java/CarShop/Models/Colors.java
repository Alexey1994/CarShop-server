package CarShop.Models;

import org.hibernate.*;
import org.hibernate.Query;
import javax.persistence.*;
import java.util.List;


@Entity
public class Colors {
    private static volatile long nextId = 0;

    @Id
    private long id;
    private String name;


    private static long generateId() {
        if(nextId != 0)
        {
            nextId++;
            return nextId;
        }

        Session session = DataBase.getSession();
        Query query = session.createQuery("SELECT id FROM Colors");
        List ids = query.list();
        Long maxId = null;

        if (ids.size() > 0)
            maxId = (Long) ids.get(ids.size() - 1);

        if (maxId == null) {
            nextId++;
            return nextId;
        }

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


    public long getId() {
        return this.id;
    }
    public void setColor(String name){ this.name = name; }
    public String getName() {
        return this.name;
    }


    public Colors(){
        this.id = generateId();
    }


    public Colors(String name){
        this.id   = generateId();
        this.name = name;
    }
}
