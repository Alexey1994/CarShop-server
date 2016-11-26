package CarShop.Models.DAO;

import java.util.List;


public interface ColorsDAO {
    public void save();

    public long getId();
    public void setColor(String name);
    public String getName();
    public ColorsDAO get(long id);
    public ColorsDAO get(String name);
    public List getAll();
}
