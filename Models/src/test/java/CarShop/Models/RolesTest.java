package CarShop.Models;


import CarShop.Models.Implementation.Roles;
import org.junit.Test;

public class RolesTest {
    @Test
    public void testGet(){
        System.out.println(RolesFactory.getDAO().get(0));
    }
}
