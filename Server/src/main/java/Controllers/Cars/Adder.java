package Controllers.Cars;

import CarShop.Models.*;
import CarShop.Models.DAO.CarBrandsDAO;
import CarShop.Models.DAO.CarModelsDAO;
import CarShop.Models.DAO.CarsDAO;
import CarShop.Models.DAO.ColorsDAO;
import CarShop.Models.Implementation.CarBrands;
import CarShop.Models.Implementation.CarModels;
import CarShop.Models.Implementation.Cars;
import Controllers.Users.AuthenticatorBySession;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.restlet.representation.Representation;
import org.restlet.resource.*;
import org.yaml.snakeyaml.util.UriEncoder;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;


public class Adder extends ServerResource {
    public static final String imagesPath = "c:/carshop/Вёрстка/images/";

    private JSONObject in;

    private CarBrandsDAO carBrand;
    private CarModelsDAO carModel;
    private ColorsDAO    carColor;
    private long         carSpeed;
    private long         carPower;
    private long         carYearOfManufacture;
    private long         carPrice;
    private List         carImages;


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


    private boolean getImages(){
        JSONArray JSONimages;

        try{
            JSONimages = (JSONArray)in.get("images");
        } catch (ClassCastException e){
            return false;
        }

        carImages = new ArrayList();

        for(int i=0; i<JSONimages.size(); ++i){

            try{
                String image = (String)JSONimages.get(i);
                //carImages.add( URLDecoder.decode(image) );
                carImages.add(image);
                //System.out.println(URLDecoder.decode(image));
            } catch (ClassCastException e){
                return false;
            }
        }

        return true;
    }


    private void addCar() throws IOException {
        CarsDAO car = CarsFactory.getDAO(carBrand.getId(), carModel.getId(), carColor.getId(), carPower, carSpeed, carPrice, carYearOfManufacture);
        car.save();

        for(int i=0; i<carImages.size(); ++i){
            String     imageName = car.getId() + "_" + i;
            FileWriter imageWriter;

            imageWriter = new FileWriter(imagesPath + imageName);
            String image = (String)carImages.get(i);
            imageWriter.write(image);

            CarImagesFactory.getDAO(car.getId(), imageName).save();

            imageWriter.close();
        }
    }


    @Post("txt")
    public String toString(Representation params) throws IOException {

        if(!AuthenticatorBySession.isAuthenticate(this.getRequest().getCookies()))
            return "{\"status\":\"User not admin\"}";

        JSONParser   parser     = new JSONParser();

        try {
            //in = (JSONObject) parser.parse(URLDecoder.decode( params.getText() ));
            in = (JSONObject) parser.parse(params.getText());
        }catch (ParseException e){
            return "{\"status\":\"error\"}";
        } catch (IOException e){
            return "{\"status\":\"error\"}";
        } catch (ClassCastException e){
            return "{\"status\":\"error\"}";
        }

        if(!getBrand() || !getModel() || !getColor() || !getSpeed() || !getPower() || !getYearOfManufacture() || !getPrice() || !getImages())
            return "{\"status\":\"error\"}";

        addCar();

        return "{\"status\":\"Ok\"}";
    }
}
