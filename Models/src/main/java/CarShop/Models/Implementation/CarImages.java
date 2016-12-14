package CarShop.Models.Implementation;

import CarShop.Models.DAO.CarImagesDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;


@Entity
public class CarImages implements CarImagesDAO {
    private static volatile long nextId = 0;

    @Id
    long   id;
    long   car_id;
    String image;


    private static long generateId() {
        if(nextId != 0)
        {
            nextId++;
            return nextId;
        }

        Session session = DataBase.getSession();
        Query query = session.createQuery("SELECT id FROM CarImages");
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


    public List getImages(long car_id){
        List        images;
        Session     session     = DataBase.getSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        images = session.createQuery("FROM CarImages WHERE car_id = " + car_id).list();
        transaction.commit();
        session.close();

        return images;
    }


    public void save() {
        Session session = DataBase.getSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        session.saveOrUpdate(this);
        transaction.commit();
        session.close();
    }


    public void setCarId(long carId){ this.car_id = carId; }
    public long getCarId(){ return this.car_id; }
    public void setImage(String image){ this.image = image; }
    public String getImage(){ return this.image; }


    public CarImages(){
        this.id = generateId();
    }


    public CarImages(long carId, String image){
        this.id     = generateId();
        this.car_id = carId;
        this.image  = image;
    }


    public String toString(){
        return this.image;
    }
}
