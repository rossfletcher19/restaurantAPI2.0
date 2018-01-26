package dao;


import models.Foodtype;

import java.util.List;

public interface FoodtypeDAO {

    //create
    void add(Foodtype name); // N
    //void addFoodTypeToRestaurant(Foodtype foodtype, Restaurant restaurant); // D

    //read
//    List<Foodtype> getAll(); // we may need this in the future. We can use it to retrieve all Foodtypes.
    // List<Restaurant> getAllRestaurantsForAFoodtype(int id); //E we will implement this soon.

    //update


    //delete
//    void deleteById(int id);

}
