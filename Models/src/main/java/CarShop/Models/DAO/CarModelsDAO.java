package CarShop.Models.DAO;


public interface CarModelsDAO {
    public void save();

    public long getId();
    public void setBrandId(long modelId);
    public long getBrandId();
    public void setModel(String type);
    public String getModel();
    public CarModelsDAO get(long id);
    public CarModelsDAO get(String model, long brandId);
}
