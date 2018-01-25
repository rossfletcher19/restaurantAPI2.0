package dao;

import models.Review;

import java.util.List;

public interface ReviewDAO {

    //create
    void add(Review review); //F

    //read
    List<Review> getAllReviewsByRestaurant(int restaurantId); // H & G
//    List<Review> getAll();

    //update
    //omit for now

    //delete
//    void deleteById(int id); //M

}
