package CarShop.Models.Implementation;

import CarShop.Models.*;
import CarShop.Models.DataBase;
import org.hibernate.*;
import org.hibernate.Query;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
public class Orders {
    private static volatile long nextId = 0;

    @Id
    private long    id;
    private long    car_id;
    private long    customer_id;
    private Date    order_date;
    private String  status;


    private static long generateId() {
        if(nextId != 0)
        {
            nextId++;
            return nextId;
        }

        Session session = DataBase.getSession();
        Query query = session.createQuery("SELECT id FROM Orders");
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
    public void setCarId(long carId){ this.car_id = carId; }
    public long getCarId(){ return this.car_id; }
    public void setCustomerId(long customerId){ this.customer_id = customerId; }
    public long getCustomerId(){ return customer_id; }
    public void setOrderDate(Date orderDate){ this.order_date = orderDate; }
    public Date getOrderDate(){ return this.order_date; }
    public void setStatus(String status){ this.status = status; }
    public String getStatus(){ return this.status; }


    public Orders(){
        this.id = generateId();
    }


    public Orders(long carId, long customerId, Date orderDate, String status){
        this.id          = generateId();
        this.car_id      = carId;
        this.customer_id = customerId;
        this.order_date  = orderDate;
        this.status      = status;
    }


    public String toString(){
        return "{" +
                "\"car_id\":" + this.car_id + "," +
                "\"customer_id\":" + this.customer_id + "," +
                "\"date\":" + this.order_date.getTime() + "," +
                "\"car_id\":\"" + this.status +
                "\"}";
    }
}
