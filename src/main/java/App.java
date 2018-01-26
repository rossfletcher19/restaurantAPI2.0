import com.google.gson.Gson;
import dao.Sql2oFoodtypeDAO;
import dao.Sql2oRestaurantDAO;
import dao.Sql2oReviewDAO;
import models.Restaurant;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.*;

/**
 * Created by Guest on 1/26/18.
 */
public class App {

    public static void main(String[] args) {
        Sql2oFoodtypeDAO foodtypeDAO;
        Sql2oRestaurantDAO restaurantDAO;
        Sql2oReviewDAO reviewDAO;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        restaurantDAO = new Sql2oRestaurantDAO(sql2o);
        foodtypeDAO = new Sql2oFoodtypeDAO(sql2o);
        reviewDAO = new Sql2oReviewDAO(sql2o);
        conn = sql2o.open();


        post("restaurants/new", "application/json", (req, res) ->{
            Restaurant restaurant = gson.fromJson(req.body(), Restaurant.class);
            restaurantDAO.add(restaurant);
            res.status(201);
            res.type("application/json");
            return gson.toJson(restaurant);
        });



    }
}
