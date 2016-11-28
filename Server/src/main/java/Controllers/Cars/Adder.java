package Controllers.Cars;

import CarShop.Models.CarBrandsFactory;
import CarShop.Models.CarModelsFactory;
import CarShop.Models.CarsFactory;
import CarShop.Models.ColorsFactory;
import CarShop.Models.DAO.CarBrandsDAO;
import CarShop.Models.DAO.CarModelsDAO;
import CarShop.Models.DAO.CarsDAO;
import CarShop.Models.DAO.ColorsDAO;
import CarShop.Models.Implementation.CarBrands;
import CarShop.Models.Implementation.CarModels;
import CarShop.Models.Implementation.Cars;
import Controllers.Users.AuthenticatorBySession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.restlet.representation.Representation;
import org.restlet.resource.*;

import java.io.IOException;
import java.net.URLDecoder;


public class Adder extends ServerResource {

    private JSONObject in;

    private CarBrandsDAO carBrand;
    private CarModelsDAO carModel;
    private ColorsDAO    carColor;
    private long         carSpeed;
    private long         carPower;
    private long         carYearOfManufacture;
    private long         carPrice;


    private boolean getBrand(){
        String brand;

        try{
            brand = (String)in.get("brand");
        } catch (ClassCastException e){
            return false;
        }

        carBrand = CarBrandsFactory.getDAO().get(brand);

        if(carBrand == null) {
            CarBrandsFactory.getDAO(brand).save();
            carBrand = CarBrandsFactory.getDAO().get(brand);
        }

        return true;
    }


    private boolean getModel(){
        String model;

        try{
            model = (String)in.get("model");
        } catch (ClassCastException e){
            return false;
        }

        carModel = CarModelsFactory.getDAO().get(model, carBrand.getId());

        if(carModel == null) {
            CarModelsFactory.getDAO(model, carBrand.getId()).save();
            carModel = CarModelsFactory.getDAO().get(model, carBrand.getId());
        }

        return true;
    }


    private boolean getColor(){
        String color;

        try{
            color = (String)in.get("color");
        } catch (ClassCastException e){
            return false;
        }

        carColor = ColorsFactory.getDAO().get(color);

        if(carColor == null) {
            ColorsFactory.getDAO(color).save();
            carColor = ColorsFactory.getDAO().get(color);
        }

        return true;
    }


    private boolean getSpeed(){
        String speed;

        try{
            speed = (String)in.get("speed");
            carSpeed = Long.parseLong(speed);
        } catch (ClassCastException e){
            return false;
        } catch (NumberFormatException e){
            return false;
        }

        return true;
    }


    private boolean getPower(){
        String power;

        try{
            power = (String)in.get("power");
            carPower = Long.parseLong(power);
        } catch (ClassCastException e){
            return false;
        } catch (NumberFormatException e){
            return false;
        }

        return true;
    }


    private boolean getYearOfManufacture(){
        String yearOfManufacture;

        try{
            yearOfManufacture = (String)in.get("year_of_manufacture");
            carYearOfManufacture = Long.parseLong(yearOfManufacture);
        } catch (ClassCastException e){
            return false;
        } catch (NumberFormatException e){
            return false;
        }

        return true;
    }


    private boolean getPrice(){
        String price;

        try{
            price = (String)in.get("price");
            carPrice = Long.parseLong(price);
        } catch (ClassCastException e){
            return false;
        } catch (NumberFormatException e){
            return false;
        }

        return true;
    }


    private void addCar(){
        CarsFactory.getDAO(carBrand.getId(), carModel.getId(), carColor.getId(), carPower, carSpeed, carPrice, carYearOfManufacture).save();
    }


    @Post("txt")
    public String toString(Representation params) throws IOException {

        if(!AuthenticatorBySession.isAuthenticate(this.getRequest().getCookies()))
            return "{\"status\":\"User not admin\"}";

        JSONParser   parser     = new JSONParser();

        try {
            in = (JSONObject) parser.parse(URLDecoder.decode( params.getText() ));
        }catch (ParseException e){
            return "{\"status\":\"error\"}";
        } catch (IOException e){
            return "{\"status\":\"error\"}";
        } catch (ClassCastException e){
            return "{\"status\":\"error\"}";
        }

        if(!getBrand() || !getModel() || !getColor() || !getSpeed() || !getPower() || !getYearOfManufacture() || !getPrice())
            return "{\"status\":\"error\"}";

        addCar();

        return "{\"status\":\"Ok\"}";
    }
}
