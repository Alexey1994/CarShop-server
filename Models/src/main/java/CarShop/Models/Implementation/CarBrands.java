package CarShop.Models.Implementation;

import CarShop.Models.DAO.CarBrandsDAO;
import org.hibernate.*;
import org.hibernate.Query;

import javax.persistence.*;
import java.util.List;


@Entity
public class CarBrands implements CarBrandsDAO {
    private static volatile long nextId = 0;

    @Id
    private long   id;
    private String brand;


    private static long generateId() {
        if(nextId != 0)
        {
            nextId++;
            return nextId;
        }

        Session session = DataBase.getSession();
        Query query = session.createQuery("SELECT id FROM CarBrands");
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
    public void setBrand(String brand){ this.brand = brand; }
    public String getBrand() {
        return this.brand;
    }

    public CarBrandsDAO get(long id){
        Session       session = DataBase.getSession();
        Transaction   transaction = session.getTransaction();
        CarBrandsDAO  carBrand    = null;
        List          list;

        transaction.begin();
        list = session.createQuery("FROM CarBrands WHERE id = " + id).list();
        transaction.commit();
        session.close();

        if(list.size()>0)
            carBrand = (CarBrandsDAO)list.get(0);

        return carBrand;
    }


    public CarBrandsDAO get(String brand){
        Session       session = DataBase.getSession();
        Transaction   transaction = session.getTransaction();
        CarBrandsDAO  carBrand    = null;
        Query         query;
        List          list;

        transaction.begin();

        query = session.createQuery("FROM CarBrands WHERE brand = :brandName");
        query.setParameter("brandName", brand);

        list = query.list();
        transaction.commit();
        session.close();

        if(list.size()>0)
            carBrand = (CarBrandsDAO)list.get(0);

        return carBrand;
    }


    public List getAll(){
        Session       session = DataBase.getSession();
        Transaction   transaction = session.getTransaction();
        List brands;

        transaction.begin();
        brands = session.createQuery("FROM CarBrands").list();
        transaction.commit();
        session.close();

        return brands;
    }


    public CarBrands() {
        this.id = generateId();
    }


    public CarBrands(String brand) {
        this.id = generateId();
        this.brand = brand;
    }


    public String toString() {
        return this.brand;
    }
}
