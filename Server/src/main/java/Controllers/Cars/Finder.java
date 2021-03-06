package Controllers.Cars;

import CarShop.Models.*;
import CarShop.Models.DAO.CarBrandsDAO;
import CarShop.Models.DAO.CarModelsDAO;
import CarShop.Models.DAO.CarsDAO;
import CarShop.Models.DAO.ColorsDAO;
import CarShop.Models.Implementation.CarBrands;
import CarShop.Models.Implementation.Cars;
import CarShop.Models.Implementation.Colors;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.restlet.data.*;
import org.restlet.representation.Representation;
import org.restlet.resource.*;
import org.json.simple.*;

import java.io.IOException;
import java.util.List;


public class Finder extends ServerResource {
    private final int numPagesOnPage = 5;

    JSONObject      in;

    private String  orderBy;
    private String  order;
    private int     page;
    private long    minPower;
    private long    maxPower;
    private long    minSpeed;
    private long    maxSpeed;
    private long    minYearOfManufacture;
    private long    maxYearOfManufacture;
    private long    minPrice;
    private long    maxPrice;
    private long    brandIds[];
    private long    modelIds[];
    private long    colorIds[];


    private void getOrderBy(){
        try {
            orderBy = (String) in.get("order_by");
        } catch (ClassCastException e){
            orderBy = "price";
        }

        if(orderBy == null || (orderBy.compareTo("price")!=0 && orderBy.compareTo("speed")!=0 && orderBy.compareTo("power")!=0 && orderBy.compareTo("year_of_manufacture")!=0))
            orderBy = "price";
    }


    private void getOrder(){
        try {
            order = (String) in.get("order");
        } catch (ClassCastException e){
            order = "asc";
        }

        if(order == null || (order.compareTo("asc") != 0 && order.compareTo("desc") != 0))
            order = "asc";
    }


    private void getMinPower(){
        try{
            minPower = Long.parseLong((String)in.get("power_begin"));

            if(minPower < 0)
                minPower = 0;

        } catch (NumberFormatException e){
            minPower = 0;
        } catch (ClassCastException e){
            minPower = 0;
        }
    }


    private void getMaxPower(){
        try{
            maxPower = Long.parseLong((String)in.get("power_end"));

            if(maxPower < 0)
                maxPower = 999999999;

        } catch (NumberFormatException e){
            maxPower = 999999999;
        } catch (ClassCastException e){
            maxPower = 999999999;
        }
    }


    private void getMinSpeed(){
        try{
            minSpeed = Long.parseLong((String)in.get("speed_begin"));

            if(minSpeed < 0)
                minSpeed = 0;

        } catch (NumberFormatException e){
            minSpeed = 0;
        } catch (ClassCastException e){
            minSpeed = 0;
        }
    }


    private void getMaxSpeed(){
        try{
            maxSpeed = Long.parseLong((String)in.get("speed_end"));

            if(maxSpeed < 0)
                maxSpeed = 999999999;

        } catch (NumberFormatException e){
            maxSpeed = 999999999;
        } catch (ClassCastException e){
            maxSpeed = 999999999;
        }
    }


    private void getMinYearOfManufacture(){
        try{
            minYearOfManufacture = Long.parseLong((String)in.get("year_of_manufacture_begin"));

            if(minYearOfManufacture < 0)
                minYearOfManufacture = 0;

        } catch (NumberFormatException e){
            minYearOfManufacture = 0;
        } catch (ClassCastException e){
            minYearOfManufacture = 0;
        }
    }


    private void getMaxYearOfManufacture(){
        try{
            maxYearOfManufacture = Long.parseLong((String)in.get("year_of_manufacture_end"));

            if(maxYearOfManufacture < 0)
                maxYearOfManufacture = 999999999;

        } catch (NumberFormatException e){
            maxYearOfManufacture = 999999999;
        } catch (ClassCastException e){
            maxYearOfManufacture = 999999999;
        }
    }


    private void getMinPrice(){
        try{
            minPrice = Long.parseLong((String)in.get("price_begin"));

            if(minPrice < 0)
                minPrice = 0;

        } catch (NumberFormatException e){
            minPrice = 0;
        } catch (ClassCastException e){
            minPrice = 0;
        }
    }


    private void getMaxPrice(){
        try{
            maxPrice = Long.parseLong((String)in.get("price_end"));

            if(maxPrice < 0)
                maxPrice = 999999999;

        } catch (NumberFormatException e){
            maxPrice = 999999999;
        } catch (ClassCastException e){
            maxPrice = 999999999;
        }
    }


    private void getPageNumber(){
        try{
            page = Integer.parseInt((String)in.get("current_page")) - 1;

            if(page < 0)
                page = 0;

        } catch (NumberFormatException e){
            page = 0;
        } catch (ClassCastException e){
            page = 0;
        }
    }


    private boolean getCarColors(){
        JSONArray colors;

        try{
            colors = (JSONArray) in.get("selected_colors");
        } catch (ClassCastException e){
            colorIds = new long[0];
            return true;
        }

        if(colors == null){
            colorIds = new long[0];
            return true;
        }

        colorIds = new long[colors.size()];

        for(int i=0; i<colors.size(); i++){
            String color;

            try{
                color = (String)colors.get(i);
            } catch (ClassCastException e){
                colorIds = new long[0];
                return true;
            }

            ColorsDAO colorDAO = ColorsFactory.getDAO().get(color);

            if(colorDAO == null)
                return false;

            colorIds[i] = colorDAO.getId();

            if(colorIds[i] == 0){
                colorIds = new long[0];
                return true;
            }
        }

        return true;
    }


    private String getImage(long carId){
        List   images       = CarImagesFactory.getDAO().getImages(carId);
        String imageString;

        if(images.size() == 0)
            return "empty.png";

        try {
            imageString = (String) images.get(0);
        } catch (ClassCastException e){
            return "empty.png";
        }

        return imageString;
    }


    private boolean getSelectedCars(){
        Object     selectedCarsObject = in.get("selected_cars");
        JSONArray  selectedCars;

        if(selectedCarsObject == null){
            brandIds = new long[0];
            modelIds = new long[0];
            return true;
        }

        try {
            selectedCars = (JSONArray) selectedCarsObject;
        } catch (ClassCastException e){
            brandIds = new long[0];
            modelIds = new long[0];
            return true;
        }

        brandIds = new long[selectedCars.size()];
        modelIds = new long[selectedCars.size()];

        for(int i=0; i<selectedCars.size(); i++){
            JSONObject   selectedCar;
            String       brandString;
            String       modelString;
            CarBrandsDAO brand;
            CarModelsDAO curModel;

            try{
                selectedCar = (JSONObject) selectedCars.get(i);

                if(selectedCar == null)
                    return false;

                brandString = (String) selectedCar.get("brand");

                if(brandString == null)
                    return false;

                modelString = (String) selectedCar.get("model");

                if(modelString == null)
                    return false;
            } catch (ClassCastException e){
                return false;
            }

            brand = CarBrandsFactory.getDAO().get(brandString);

            if(brand == null)
                return false;

            brandIds[i] = brand.getId();

            curModel = CarModelsFactory.getDAO().get(modelString, brand.getId());

            if(curModel == null) {
                curModel = CarModelsFactory.getDAO().get(0);

                if(modelString.compareTo(curModel.toString()) != 0)
                    return false;
            }

            modelIds[i] = curModel.getId();
        }

        for(int i=0; i<brandIds.length; i++)
            if (brandIds[i] == 0) {
                brandIds = new long[0];
                modelIds = new long[0];
            }

        return true;
    }


    private String getCars(){
        List pages = CarsFactory.getDAO().findCars(
                numPagesOnPage,
                page * numPagesOnPage,
                brandIds,
                modelIds,
                colorIds,
                minPower,
                maxPower,
                minSpeed,
                maxSpeed,
                minPrice,
                maxPrice,
                minYearOfManufacture,
                maxYearOfManufacture,
                orderBy,
                order);

        List     cars = (List)pages.get(0);
        Long     numPages = (Long)pages.get(1);
        CarsDAO  car;
        String   result;

        result = "{\"pages\":" + (numPages / numPagesOnPage + 1)  + ", \"result\":[";

        for(int i=0; i<cars.size() - 1; ++i) {
            car = (CarsDAO) cars.get(i);
            result += car.toString() + ",";
        }

        if(cars.size() > 0) {
            car = (CarsDAO) cars.get( cars.size() - 1 );
            result += car.toString();
        }

        return result + "]}";
    }


    @Post("txt")
    public String toString(Representation params) throws IOException {
        JSONParser parser = new JSONParser();
        String     paramsString = params.getText();

        try{
            in = (JSONObject) parser.parse(paramsString);
        }catch(ParseException e){
            return "{}";
        }

        getOrderBy();
        getOrder();
        getPageNumber();
        getMinPower();
        getMaxPower();
        getMinSpeed();
        getMaxSpeed();
        getMinYearOfManufacture();
        getMaxYearOfManufacture();
        getMinPrice();
        getMaxPrice();

        if(!getCarColors() || !getSelectedCars())
            return "{}";

        return getCars();
    }

}
