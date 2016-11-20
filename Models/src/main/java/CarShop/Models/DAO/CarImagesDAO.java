package CarShop.Models.DAO;

import java.util.List;


public interface CarImagesDAO {
    public void setCarId(long carId);
    public long getCarId();
    public void setImage(String image);
    public String getImage();
    public List getImages(long car_id);
    public void save();
}
