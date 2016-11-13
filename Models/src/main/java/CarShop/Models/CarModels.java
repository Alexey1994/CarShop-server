package CarShop.Models;

import org.hibernate.*;
import org.hibernate.Query;

import javax.persistence.*;
import java.util.List;


@Entity
public class CarModels {
    private static volatile long nextId = 0;

    @Id
    private long   id;
    private String model;


    private static long generateId() {
        if(nextId != 0)
        {
            nextId++;
            return nextId;
        }

        Session session = DataBase.getSession();
        Query query = session.createQuery("SELECT id FROM CarModels");
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


    public long getId() { return this.id; }
    public void setModel(String model){ this.model = model; }
    public String getModel() {
        return this.model;
    }


    public CarModels() {
        this.id = generateId();
    }


    public CarModels(String model) {
        this.id = generateId();
        this.model = model;
    }


    public String toString() {
        return "{" +
                "\"model\":\"" + this.model +
                "\"}";
    }
}
