package CarShop.Models.DAO;


import java.sql.Date;
import java.util.List;

public interface OrdersDAO {
    public void save();
    public long getId();
    public List getAll();
    public List getAll(long customerId);
    public void setCarId(long carId);
    public long getCarId();
    public void setCustomerId(long customerId);
    public long getCustomerId();
    public void setOrderDate(Date orderDate);
    public Date getOrderDate();
    public void setStatusId(long statusId);
    public long getStatusId();
}
