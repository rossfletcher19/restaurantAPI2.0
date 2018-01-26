package dao;

import models.Foodtype;
import models.Restaurant;
import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oRestaurantDAO implements RestaurantDAO {

    private final Sql2o sql2o;

    public Sql2oRestaurantDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Restaurant restaurant) {
        String sql = "INSERT INTO restaurants (name, address, zipcode, phone, website, email) VALUES (:name, :address, :zipcode, :phone, :website, :email)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(restaurant)
                    .executeUpdate()
                    .getKey();
            restaurant.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<Restaurant> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM restaurants")
                    .executeAndFetch(Restaurant.class);
        }
    }

    @Override
    public Restaurant findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM restaurants WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Restaurant.class);
        }
    }

    @Override
    public void update(int id, String name, String address, String zipcode, String phone, String website, String email) {
        String sql = "UPDATE restaurants SET name = :name, address = :address, zipcode = :zipcode, phone = :phone, website = :website, email = :email WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("address", address)
                    .addParameter("zipcode", zipcode)
                    .addParameter("phone", phone)
                    .addParameter("website", website)
                    .addParameter("email", email)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from restaurants WHERE id = :id";
        String deleteJoin = "DELETE from restaurants_foodtypes WHERE restaurantid = :restaurantid";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("restaurantid", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
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
    public void addRestaurantToFoodType(Restaurant restaurant, Foodtype foodtype) {
        String sql = "INSERT INTO restaurants_foodtypes (restaurantid, foodtypeid) VALUES (:restaurantid, :foodtypeid)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("restaurantid", restaurant.getId())
                    .addParameter("foodtypeid", foodtype.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Foodtype> getAllFoodtypesForARestaurant(int restaurantid) {
        ArrayList<Foodtype> foodtypes = new ArrayList<>();

        String joinQuery = "SELECT foodtypeid FROM restaurants_foodtypes WHERE restaurantid = :restaurantid";

        try (Connection con = sql2o.open()) {
            List<Integer> allFoodtypesIds = con.createQuery(joinQuery)
                    .addParameter("restaurantid", restaurantid)
                    .executeAndFetch(Integer.class);
            for (Integer foodtypeid : allFoodtypesIds) {
                String foodtypeQuery = "SELECT * FROM foodtypes WHERE id = :foodtypeid";
                foodtypes.add(
                        con.createQuery(foodtypeQuery)
                        .addParameter("foodtypeid", foodtypeid)
                        .executeAndFetchFirst(Foodtype.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return foodtypes;
    }


}
