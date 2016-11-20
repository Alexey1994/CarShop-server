package CarShop.Models;

import CarShop.Models.CarImagesFactory;
import CarShop.Models.DAO.CarImagesDAO;
import org.junit.Test;
import java.util.List;


public class CarImagesTest {

    @Test
    public void testSaveImages(){
        CarImagesDAO image = CarImagesFactory.getDAO(1, "t");

        image.save();
    }


    @Test
    public void testGetImages(){
        List images = CarImagesFactory.getDAO().getImages(1);

        for(int i=0; i<images.size(); i++){
            System.out.println(images.get(i));
        }
    }
}
