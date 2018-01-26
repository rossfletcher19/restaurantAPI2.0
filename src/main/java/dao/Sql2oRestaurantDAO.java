package dao;

import models.Restaurant;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
//
//    @Override
//    public Restaurant findById(int id) {
//        return null;
//    }
//
//    @Override
//    public void update(int id, String name, String address, String zipcode, String phone, String website, String email) {
//
//    }
//
//    @Override
//    public void deleteById(int id) {
//
//    }
}
