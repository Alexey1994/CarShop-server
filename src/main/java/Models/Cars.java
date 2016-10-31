package Models;

import java.util.List;
import java.util.ArrayList;


public class Cars {
    private long id;
    private long colorId;
    private long typeId;
    private long modelId;
    private long power;
    private long yearOfManufacture;
    private long speed;


    public List findCars(){
        List cars = new ArrayList<Cars>();

        cars.add(new Cars());

        return cars;
    }


    public void Cars(){

    }


    public void Cars(CarModels model,
                     CarTypes  type,
                     Colors    color,
                     long      power,
                     long      yearOfManufacture,
                     long      speed)
    {
        this.modelId           = model.getId();
        this.typeId            = type.getId();
        this.colorId           = color.getId();
        this.power             = power;
        this.yearOfManufacture = yearOfManufacture;
        this.speed             = speed;
    }
}
