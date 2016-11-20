package CarShop.Models.Implementation;

import CarShop.Models.DAO.*;
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
public class Cars implements CarsDAO {
    private static volatile long nextId = 0;

    @Id
    private long   id;
    private long   brand_id;
    private long   model_id;
    private long   color_id;
    private long   power;
    private long   speed;
    private long   price;
    private long   year_of_manufacture;


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


    public List findCars(int  maxResults,
                         long colorIds[],
                         long brandIds[],
                         long modelIds[],
                         long powerMin,
                         long powerMax,
                         long speedMin,
                         long speedMax,
                         long priceMin,
                         long priceMax,
                         long yearOfManufactureMin,
                         long yearOfManufactureMax) {

        Session session = DataBase.getSession();
        Finder finder = new Finder("FROM Cars ");
        String finderQuery;
        Query dbQuery;
        List cars;

        finder.addArrayParametersFilter("brand_id", brandIds);
        finder.addArrayParametersFilter("model_id", modelIds);
        finder.addArrayParametersFilter("color_id", colorIds);
        finder.addRangedParameterFilter("power", powerMin, powerMax);
        finder.addRangedParameterFilter("speed", speedMin, speedMax);
        finder.addRangedParameterFilter("price", priceMin, priceMax);
        finder.addRangedParameterFilter("year_of_manufacture", yearOfManufactureMin, yearOfManufactureMax);

        finderQuery = finder.getQuery();

        dbQuery = session.createQuery(finderQuery);
        dbQuery.setMaxResults(5);
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


    public void delete() {
        Session session = DataBase.getSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        session.delete(this);
        transaction.commit();
        session.close();
    }


    public Cars get(long id) {
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
        this.id = generateId();
    }


    public Cars(long brandId, long modelId, long colorId, long power, long speed, long price, long yearOfManufacture) {
        this.id = generateId();

        this.brand_id            = brandId;
        this.model_id            = modelId;
        this.color_id            = colorId;
        this.power               = power;
        this.speed               = speed;
        this.price               = price;
        this.year_of_manufacture = yearOfManufacture;
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
    public void setBrandId(long brandId) {
        this.brand_id = brandId;
    }
    public long getBrandId() {
        return this.brand_id;
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
    public void setSpeed(long speed) {
        this.speed = speed;
    }
    public long getSpeed() {
        return this.speed;
    }
    public void setPrice(long price) {
        this.price = price;
    }
    public long getPrice() {
        return this.price;
    }
    public void setYearOfManufacture(long yearOfManufacture) {
        this.year_of_manufacture = yearOfManufacture;
    }
    public long getYearOfManufacture(long yearOfManufacture) {
        return this.year_of_manufacture;
    }


    public String toString() {
        List         images = new CarImages().getImages(this.id);
        String       imagesString = "[";
        CarBrandsDAO brand        = new CarBrands().get(this.brand_id);
        CarModelsDAO model        = new CarModels().get(this.model_id);
        ColorsDAO color        = new Colors().get(this.color_id);
        String       brandString  = "-";
        String       modelString  = "-";
        String       colorString  = "-";
        CarImagesDAO image;

        for(int i=0; i<images.size() - 1; i++)
            imagesString += "\"" + images.get(i) + "\",";

        if(images.size() > 0)
            imagesString += "\"" + images.get(images.size() - 1) + "\"";

        imagesString += "]";

        if(brand != null)
            brandString = brand.toString();

        if(model != null)
            modelString = model.toString();

        if(color != null)
            colorString = color.toString();

        return "{" +
                "\"id\":" + this.id + "," +
                "\"brand\":\"" + brandString + "\"," +
                "\"model\":\"" + modelString + "\"," +
                "\"color\":\"" + colorString + "\"," +
                "\"power\":" + this.power + "," +
                "\"speed\":" + this.speed + "," +
                "\"price\":" + this.price + "," +
                "\"images\":" + imagesString + "," +
                "\"year_of_manufacture\":" + this.year_of_manufacture +
                "}";
    }
}
