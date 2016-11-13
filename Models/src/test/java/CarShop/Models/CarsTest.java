package CarShop.Models;

import CarShop.Models.Cars;
import CarShop.Models.Finder;
import org.junit.Assert;
import org.junit.Test;


public class CarsTest {

    @Test
    public void testFindQuery(){
        Finder finder       = new Finder("");
        long   parameters[] = new long[2];

        finder.addArrayParametersFilter("a", parameters);
        Assert.assertEquals(finder.getQuery(), "WHERE (a=0 OR a=0)");

        finder.addArrayParametersFilter("a", parameters);
        Assert.assertEquals(finder.getQuery(), "WHERE (a=0 OR a=0) AND (a=0 OR a=0)");

        finder = new Finder("");

        finder.addRangedParameterFilter("a", 1, 0);
        Assert.assertEquals(finder.getQuery(), "");

        finder.addRangedParameterFilter("a", 1, 2);
        Assert.assertEquals(finder.getQuery(), "WHERE a>=1 AND a<=2");
    }


    @Test
    public void testFindCars(){
        long carsColorIds[] = new long[3];

        carsColorIds[0] = 1;
        carsColorIds[1] = 2;
        carsColorIds[2] = 3;

        Cars.findCars(carsColorIds, carsColorIds, carsColorIds, 100, 200, 200, 320, 2010, 2015);
    }


    @Test
    public void testGetCar(){
        System.out.println(Cars.get(1));
    }


    @Test
    public void testSaveCars(){
        Cars car = new Cars(0, 1, 1, 300, 250, 2014, "a.img");
        car.save();
    }


    @Test
    public void testUpdate(){
        Cars car = Cars.get(1);

        if(car == null)
            return;

        car.setSpeed(400);
        car.save();
    }


    @Test
    public void testDelete(){
        Cars car = new Cars(0, 1, 1, 300, 250, 2014, "a.img");

        car.save();
        Cars.delete(car.getId());
    }
}
