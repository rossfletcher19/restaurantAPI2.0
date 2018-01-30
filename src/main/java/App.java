import com.google.gson.Gson;
import dao.Sql2oFoodtypeDAO;
import dao.Sql2oRestaurantDAO;
import dao.Sql2oReviewDAO;
import exceptions.ApiException;
import models.Foodtype;
import models.Restaurant;
import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        Sql2oFoodtypeDAO foodtypeDAO;
        Sql2oRestaurantDAO restaurantDAO;
        Sql2oReviewDAO reviewDAO;
        Connection conn;
        Gson gson = new Gson();
        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        restaurantDAO = new Sql2oRestaurantDAO(sql2o);
        foodtypeDAO = new Sql2oFoodtypeDAO(sql2o);
        reviewDAO = new Sql2oReviewDAO(sql2o);
        conn = sql2o.open();

//        Get and Post Routes

//        get("/restaurants"
//        get("/restaurants/:id"
//        get("/restaurants/:id/reviews"
//        get("/restaurants/:restaurantId/foodtypes"
//        get("/foodtypes"
//        get("/foodtypes/:foodtypeId/restaurants"

//        post("/restaurants/new"
//        post("/restaurants/:restaurantId/reviews/new"
//        post("/restaurants/:restaurantId/foodtype/:foodtypeId"
//        post("/foodtypes/new"


        get("/restaurants", "application/json", (req, res) -> {
            if(restaurantDAO.getAll().size() > 0){
                return gson.toJson(restaurantDAO.getAll());
            }
            else {
                return "{\"message\":\"I'm sorry, but no restaurants are currently listed in the database.\"}";
            }
        });

        get("/restaurants/:id", "application/json", (req, res) -> {
            int restaurantId = Integer.parseInt(req.params("id"));
            Restaurant restaurantToFind = restaurantDAO.findById(restaurantId);

            if (restaurantToFind == null){
                throw new ApiException(404, String.format("No restaurant with the id: \"%s\" exists", req.params("id")));
            }

            return gson.toJson(restaurantToFind);
        });


        post("/restaurants/new", "application/json", (req, res) -> {
            Restaurant restaurant = gson.fromJson(req.body(), Restaurant.class);
            restaurantDAO.add(restaurant);
            res.status(201);
            return gson.toJson(restaurant);
        });

        post("/foodtypes/new", "application/json", (req, res) -> {
            Foodtype foodtype = gson.fromJson(req.body(), Foodtype.class);
            foodtypeDAO.add(foodtype);
            res.status(201);;
            return gson.toJson(foodtype);
        });

        get("/foodtypes", "application/json", (req, res) -> {
            return gson.toJson(foodtypeDAO.getAll());
        });

        get("/restaurants/:id/reviews", "application/json", (req, res) -> {
            int restaurantId = Integer.parseInt(req.params("id"));
            Restaurant restaurantToFind = restaurantDAO.findById(restaurantId);
            List<Review> allReviews;
            allReviews = reviewDAO.getAllReviewsByRestaurant(restaurantId);
            return gson.toJson(allReviews);
        });

        post("/restaurants/:restaurantId/reviews/new", "application/json", (req, res) -> {
            int restaurantId = Integer.parseInt(req.params("restaurantId"));
            Review review = gson.fromJson(req.body(), Review.class);
            review.setRestaurantId(restaurantId);
            reviewDAO.add(review);
            res.status(201);
            return gson.toJson(review);
        });


        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });



        after((req, res) ->{
            res.type("application/json");
        });







    }
}
