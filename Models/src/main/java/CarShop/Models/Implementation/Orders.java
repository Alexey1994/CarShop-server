package CarShop.Models.Implementation;

import CarShop.Models.DAO.OrdersDAO;
import org.hibernate.*;
import org.hibernate.Query;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
public class Orders implements OrdersDAO {

    @Id
    private long    id;
    private long    car_id;
    private long    customer_id;
    private Date    order_date;
    private long    status_id;


    private long generateId(){
        return new java.util.Date().getTime();
    }


    public void save() {
        Session session = DataBase.getSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        session.saveOrUpdate(this);
        transaction.commit();
        session.close();
    }


    public List getAll(){
        Session     session     = DataBase.getSession();
        Transaction transaction = session.getTransaction();
        List        list;

        transaction.begin();
        list = session.createQuery("FROM Orders").list();
        transaction.commit();
        session.close();

        return list;
    }


    public List getAll(long customerId){
        Session     session     = DataBase.getSession();
        Transaction transaction = session.getTransaction();
        List        list;

        transaction.begin();
        list = session.createQuery("FROM Orders WHERE customer_id=" + customerId).list();
        transaction.commit();
        session.close();

        return list;
    }


    public long getId(){ return this.id; }
    public void setCarId(long carId){ this.car_id = carId; }
    public long getCarId(){ return this.car_id; }
    public void setCustomerId(long customerId){ this.customer_id = customerId; }
    public long getCustomerId(){ return customer_id; }
    public void setOrderDate(Date orderDate){ this.order_date = orderDate; }
    public Date getOrderDate(){ return this.order_date; }
    public void setStatusId(long statusId){ this.status_id = statusId; }
    public long getStatusId(){ return this.status_id; }


    public Orders(){
        this.id = generateId();
    }


    public Orders(long carId, long customerId, Date orderDate, long statusId){
        this.id          = generateId();
        this.car_id      = carId;
        this.customer_id = customerId;
        this.order_date  = orderDate;
        this.status_id   = statusId;
    }


    public String toString(){
        return "{" +
                "\"car_id\":" + this.car_id + "," +
                "\"customer_id\":" + this.customer_id + "," +
                "\"date\":" + this.order_date.getTime() +
                "}";
    }
}
