package dao;

import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oReviewDAO implements ReviewDAO {

    private final Sql2o sql2o;

    public Sql2oReviewDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Review review) {
        String sql = "INSERT INTO reviews (writtenby, content, rating, restaurantid, createdat) VALUES (:writtenBy, :content, :rating, :restaurantId, :createdat)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(review)
                    .executeUpdate()
                    .getKey();
            review.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Review> getAllReviewsByRestaurant(int restaurantId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM reviews WHERE restaurantid = :restaurantId")
                    .addParameter("restaurantId", restaurantId)
                    .executeAndFetch(Review.class);
        }
    }

    @Override
    public List<Review> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM reviews")
                    .executeAndFetch(Review.class);
        }
    }

    @Override
    public int avgRestaurantRating(int restaurantId) {
        String sql = "SELECT * FROM reviews WHERE restaurantId = :restaurantId";
        int reviewTotal = 0;
        try (Connection con = sql2o.open()) {
            List<Review> reviews = con.createQuery(sql)
                    .addParameter("restaurantId", restaurantId)
                    .executeAndFetch(Review.class);
            for ( int i = 0; i < reviews.size(); i++) {
                reviewTotal += reviews.get(i).getRating();
            } return reviewTotal / reviews.size();

        }

    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from reviews WHERE  id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}
