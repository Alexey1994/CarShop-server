package CarShop.Models.Implementation;

import CarShop.Models.DAO.CartDAO;
import org.hibernate.*;
import org.hibernate.Query;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class Cart implements CartDAO {

    @Id
    private long id;
    private long car_id;
    private long customer_id;


    private long generateId(){
        return new Date().getTime();
    }


    public CartDAO get(long id){
        Session     session     = DataBase.getSession();
        Transaction transaction = session.getTransaction();
        List        products;
        CartDAO     cart;

        transaction.begin();

        products = session.createQuery("FROM Cart WHERE id=" + id).list();

        if(products.size() != 0)
            cart = (CartDAO)products.get(0);
        else
            cart = null;

        transaction.commit();
        session.close();

        return cart;
    }


    public void save() {
        Session session = DataBase.getSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        session.saveOrUpdate(this);
        transaction.commit();
        session.close();
    }


    public void delete(){
        Session session = DataBase.getSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        session.delete(this);
        transaction.commit();
        session.close();
    }


    public List getOrders(long customerId){
        Session     session     = DataBase.getSession();
        Transaction transaction = session.getTransaction();
        Query       query;
        List        orders;

        transaction.begin();

        query = session.createQuery("FROM Cart WHERE customer_id=:customerId");
        query.setParameter("customerId", customerId);

        orders = query.list();
        transaction.commit();
        session.close();

        return orders;
    }


    public long getId(){ return this.id; }
    public void setCustomerId(long customerId){ this.customer_id = customerId; }
    public long getCustomerId(){ return this.customer_id; }
    public void setcarId(long carId){ this.car_id = carId; }
    public long getCarId(){ return this.car_id; }


    public Cart(){
        this.id = generateId();
    }


    public Cart(long customerId, long carId){
        this.id           = generateId();
        this.customer_id  = customerId;
        this.car_id       = carId;
    }


    public String toString(){
        return "{" +
                "\"id\":\"" + this.id + "\"," +
                "\"customer_id\":\"" + this.customer_id + "\"," +
                "\"car_id\":\"" + this.car_id +
                "\"}";
    }
}
