package CarShop.Models.DAO;


import java.util.List;

public interface CartDAO {
    public CartDAO get(long id);
    public void save();
    public void delete();
    public long getId();
    public void setCustomerId(long customerId);
    public long getCustomerId();
    public void setcarId(long carId);
    public long getCarId();
    public List getOrders(long customerId);
}
