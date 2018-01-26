package dao;

import models.Review;

import javax.print.DocFlavor;
import java.util.List;

public interface ReviewDAO {

    //create
    void add(Review review); //F

    //read
    List<Review> getAllReviewsByRestaurant(int restaurantId); // H & G
    List<Review> getAll();

    //update
    // no update yet

    //delete
    void deleteById(int id); //M

}
