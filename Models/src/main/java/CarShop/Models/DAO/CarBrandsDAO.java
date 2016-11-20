package CarShop.Models.DAO;

import java.util.List;


public interface CarBrandsDAO {
    public void save();

    public long getId();
    public void setBrand(String brand);
    public String getBrand();
    public CarBrandsDAO get(long id);
    CarBrandsDAO get(String brand);
    public List getAll();
}
