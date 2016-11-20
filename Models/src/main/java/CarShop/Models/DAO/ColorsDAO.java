package CarShop.Models.DAO;


public interface ColorsDAO {
    public void save();

    public long getId();
    public void setColor(String name);
    public String getName();
    public ColorsDAO get(long id);
}
