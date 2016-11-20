package CarShop.Models.Implementation;

import CarShop.Models.DAO.CarModelsDAO;
import org.hibernate.*;
import org.hibernate.Query;
import javax.persistence.*;
import java.util.List;


@Entity
public class CarModels implements CarModelsDAO {
    private static volatile long nextId = 0;

    @Id
    private long   id;
    private String model;
    private long   brand_id;


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
    public void setBrandId(long modelId){ this.brand_id = modelId; }
    public long getBrandId(){ return this.brand_id; }
    public void setModel(String model){ this.model = model; }
    public String getModel() { return this.model; }


    public CarModelsDAO get(long id){
        Session       session = DataBase.getSession();
        Transaction   transaction = session.getTransaction();
        CarModelsDAO  carModel    = null;
        List          list;

        transaction.begin();
        list = session.createQuery("FROM CarModels WHERE id = " + id).list();
        transaction.commit();
        session.close();

        if(list.size()>0)
            carModel = (CarModelsDAO)list.get(0);

        return carModel;
    }


    public CarModelsDAO get(String model, long brandId){
        Session       session = DataBase.getSession();
        Transaction   transaction = session.getTransaction();
        CarModelsDAO  carModel    = null;
        Query         query;
        List          list;

        transaction.begin();

        query = session.createQuery("FROM CarModels WHERE model = :carModel AND brand_id = " + brandId);
        query.setParameter("carModel", model);

        list = query.list();
        transaction.commit();
        session.close();

        if(list.size()>0)
            carModel = (CarModelsDAO)list.get(0);

        return carModel;
    }


    public List getModelsWithBrand(long brandId){
        Session       session = DataBase.getSession();
        Transaction   transaction = session.getTransaction();
        CarModelsDAO  carModel    = null;
        Query         query;
        List          list;

        transaction.begin();

        list = session.createQuery("FROM CarModels WHERE brand_id = " + brandId).list();

        transaction.commit();
        session.close();

        return list;
    }


    public CarModels(){
        this.id = generateId();
    }


    public CarModels(String model, long brandId){
        this.id       = generateId();
        this.model    = model;
        this.brand_id = brandId;
    }


    public String toString(){
        return this.model;
    }
}
