package CarShop.Models.DAO;

import java.util.List;



public interface CarsDAO {

    public List findCars(int  maxResults,
                         long brandIds[],
                         long modelIds[],
                         long colorIds[],
                         long powerMin,
                         long powerMax,
                         long speedMin,
                         long speedMax,
                         long priceMin,
                         long priceMax,
                         long yearOfManufactureMin,
                         long yearOfManufactureMax,
                         String orderBy,
                         String order);


    public void save();
    public void delete();
    public CarsDAO get(long id);


    public long getId();
    public void setColorId(long colorId);
    public long getColorId();
    public void setBrandId(long brandId);
    public long getBrandId();
    public void setModelId(long modelId);
    public long getModelId();
    public long getPower();
    public void setPower(long power);
    public void setSpeed(long speed);
    public long getSpeed();
    public void setPrice(long price);
    public long getPrice();
    public void setYearOfManufacture(long yearOfManufacture);
    public long getYearOfManufacture(long yearOfManufacture);
}
