package CarShop.Models;

import org.hibernate.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;


class Finder {
    private boolean isFirstParameter;
    private String query;


    public void addArrayParametersFilter(String parameter, long parameters[]) {
        if (parameters.length == 0)
            return;

        if (!isFirstParameter)
            query += " AND ";
        else
            query += "WHERE ";

        query += "(";

        query += parameter + "=" + parameters[0];
        isFirstParameter = false;

        for (int i = 1; i < parameters.length; i++) {
            if (!isFirstParameter)
                query += " OR ";
            else
                query += "WHERE ";

            query += parameter + "=" + parameters[i];
            isFirstParameter = false;
        }

        query += ")";
    }


    public void addRangedParameterFilter(String parameter, long min, long max) {
        if (min > max)
            return;

        if (!isFirstParameter)
            query += " AND ";
        else
            query += "WHERE ";

        query += parameter + ">=" + min + " AND " + parameter + "<=" + max;
        isFirstParameter = false;
    }


    public String getQuery() {
        return query;
    }


    public Finder(String query) {
        this.query = query;
        isFirstParameter = true;
    }
}


@Entity
public class Cars {
    private static volatile long nextId = 0;

    @Id
    private long   id;
    private long   type_id;
    private long   model_id;
    private long   color_id;
    private long   power;
    private long   speed;
    private long   year_of_manufacture;
    private String image;


    private static long generateId() {
        if(nextId != 0)
        {
            nextId++;
            return nextId;
        }

        Session session = DataBase.getSession();
        Query query = session.createQuery("SELECT id FROM Cars");
        List ids = query.list();
        Long maxId = null;

        if (ids.size() > 0)
            maxId = (Long) ids.get(ids.size() - 1);

        if (maxId == null) {
            nextId++;
            return nextId;
        }

        nextId = maxId.longValue() + 1;

        return nextId;
    }


    public static List findCars(long colorIds[],
                                long typeIds[],
                                long modelIds[],
                                long powerMin,
                                long powerMax,
                                long speedMin,
                                long speedMax,
                                long yearOfManufactureMin,
                                long yearOfManufactureMax) {

        Session session = DataBase.getSession();
        Finder finder = new Finder("FROM Cars ");
        String finderQuery;
        Query dbQuery;
        List cars;

        finder.addArrayParametersFilter("type_id", typeIds);
        finder.addArrayParametersFilter("model_id", modelIds);
        finder.addArrayParametersFilter("color_id", colorIds);
        finder.addRangedParameterFilter("power", powerMin, powerMax);
        finder.addRangedParameterFilter("speed", speedMin, speedMax);
        finder.addRangedParameterFilter("year_of_manufacture", yearOfManufactureMin, yearOfManufactureMax);

        finderQuery = finder.getQuery();

        dbQuery = session.createQuery(finderQuery);
        cars = dbQuery.list();
        session.close();

        return cars;
    }


    public void save() {
        Session session = DataBase.getSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        session.saveOrUpdate(this);
        transaction.commit();
        session.close();
    }


    public static void delete(long id) {
        Cars car = Cars.get(id);
        Session session = DataBase.getSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        session.delete(car);
        transaction.commit();
        session.close();
    }


    public static Cars get(long id) {
        Session session = DataBase.getSession();
        Query query = session.createQuery("FROM Cars WHERE id=" + id);
        List cars = query.list();
        Cars car = null;

        if (cars.size() > 0)
            car = (Cars) query.list().get(0);

        session.close();

        return car;
    }


    public Cars() {
        //this.id = this.nextId;
        //this.nextId++;
        this.id = generateId();
    }


    public Cars(long typeId, long modelId, long colorId, long power, long speed, long yearOfManufacture, String image) {
        //this.id = this.nextId;
        //this.nextId++;
        this.id = generateId();

        this.type_id             = typeId;
        this.model_id            = modelId;
        this.color_id            = colorId;
        this.power               = power;
        this.speed               = speed;
        this.year_of_manufacture = yearOfManufacture;
        this.image               = image;
    }


    public long getId() {
        return this.id;
    }
    public void setColorId(long colorId) {
        this.color_id = colorId;
    }
    public long getColorId() {
        return this.color_id;
    }
    public void setTypeId(long typeId) {
        this.type_id = typeId;
    }
    public long getTypeId() {
        return this.type_id;
    }
    public void setModelId(long modelId) {
        this.model_id = modelId;
    }
    public long getModelId() {
        return this.model_id;
    }
    public long getPower() {
        return this.power;
    }
    public void setPower(long power) {
        this.power = power;
    }
    public void setYearOfManufacture(long yearOfManufacture) {
        this.year_of_manufacture = yearOfManufacture;
    }
    public long getYearOfManufacture(long yearOfManufacture) {
        return this.year_of_manufacture;
    }
    public void setSpeed(long speed) {
        this.speed = speed;
    }
    public long getSpeed() {
        return this.speed;
    }
    public void setImage(String image){ this.image = image; }
    public String getImage(){ return this.image; }


    public String toString() {
        return "{" +
                "\"id\":" + this.id + "," +
                "\"type_id\":" + this.type_id + "," +
                "\"model_id\":" + this.model_id + "," +
                "\"color_id\":" + this.color_id + "," +
                "\"power\":" + this.power + "," +
                "\"speed\":" + this.speed + "," +
                "\"year_of_manufacture\":" + this.year_of_manufacture + "," +
                "\"image\":\"" + this.image +
                "\"}";
    }
}
