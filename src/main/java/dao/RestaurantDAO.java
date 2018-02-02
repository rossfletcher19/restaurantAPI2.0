package dao;

import models.Foodtype;
import models.Restaurant;

import java.util.List;

public interface RestaurantDAO {

    //create
    void add (Restaurant restaurant); //J
    void addRestaurantToFoodType(Restaurant restaurant, Foodtype foodtype); //D & E

    //read
    List<Restaurant> getAll(); //Aa
    List<Restaurant> findByZipcode(String zipcode); // A
    List<Foodtype> getAllFoodtypesForARestaurant(int id); //D & E

    Restaurant findById(int id); //B & C

    //update
    void update(int id, String name, String address, String zipcode, String phone, String website, String email); //K

    //delete
    void deleteById(int id); //L

}
