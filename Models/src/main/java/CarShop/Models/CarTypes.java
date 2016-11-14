package CarShop.Models;

import org.hibernate.*;
import org.hibernate.Query;
import javax.persistence.*;
import java.util.List;


@Entity
public class CarTypes {
    private static volatile long nextId = 0;

    @Id
    private long   id;
    private String type;
    private long   model_id;


    private static long generateId() {
        if(nextId != 0)
        {
            nextId++;
            return nextId;
        }

        Session session = DataBase.getSession();
        Query query = session.createQuery("SELECT id FROM CarTypes");
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


    public long getId() { return this.id; }
    public void setModelId(long modelId){ this.model_id = modelId; }
    public long getModelId(){ return this.model_id; }
    public void setType(String type){ this.type = type; }
    public String getType() {
        return this.type;
    }


    public CarTypes(){
        this.id = generateId();
    }


    public CarTypes(String type){
        this.id = generateId();
        this.type = type;
    }


    public String toString(){
        return this.type;
    }
}
