package CarShop.Models;

import CarShop.Models.CarsFactory;
import CarShop.Models.DAO.CarsDAO;
import CarShop.Models.Implementation.Cars;
import CarShop.Models.Implementation.Finder;
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

        CarsDAO c = new Cars();
        c.findCars(100, carsColorIds, carsColorIds, carsColorIds, 100, 200, 200, 320, 1000, 5000, 2010, 2015);
    }


    @Test
    public void testGetCar(){
        System.out.println(CarsFactory.getDAO().get(1));
    }


    @Test
    public void testSaveCars(){

        CarsDAO car = CarsFactory.getDAO(1, 1, 1, 300, 250, 20000, 2014);
        car.save();
    }


    @Test
    public void testUpdate(){
        CarsDAO car = CarsFactory.getDAO().get(1);

        if(car == null)
            return;

        car.setSpeed(400);
        car.save();
    }


    @Test
    public void testDelete(){
        CarsDAO car = CarsFactory.getDAO(0, 1, 1, 300, 250, 10000, 2014);

        car.save();
        car.delete();
    }
}
