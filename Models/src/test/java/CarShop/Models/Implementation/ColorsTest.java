package CarShop.Models.Implementation;

import CarShop.Models.ColorsFactory;
import CarShop.Models.DAO.ColorsDAO;
import CarShop.Models.Implementation.Colors;
import org.junit.Test;


public class ColorsTest {
    @Test
    public void testSaveColor(){
        ColorsDAO color = ColorsFactory.getDAO("red");

        color.save();
    }
}
