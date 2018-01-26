package dao;

import models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oRestaurantDAOTest {

    private Connection conn;
    private Sql2oRestaurantDAO restaurantDAO;
    private Sql2oReviewDAO reviewDAO;
    private Sql2oFoodtypeDAO foodtypeDAO;

    public Restaurant setupRestaurant (){
        return new Restaurant("Screen Door", "1234 SE Burnside", "97232", "503-876-5309", "http://screendoor.com", "screendoor@email.com");
    }

    public Review setupNewReview() {
        return new Review("Ross F.", "Great Southern Food!", 100, 0);
    }

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "","");
        restaurantDAO = new Sql2oRestaurantDAO(sql2o);
        foodtypeDAO = new Sql2oFoodtypeDAO(sql2o);
        reviewDAO = new Sql2oReviewDAO(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addFoodSetsId() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        int originalRestaurantID = testRestaurant.getId();
        restaurantDAO.add(testRestaurant);
        assertNotEquals(originalRestaurantID, testRestaurant.getId());
    }

    @Test
    public void getAllReturnsAllRestaurantsAdded() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        Restaurant otherRestaurant = setupRestaurant();
        restaurantDAO.add(testRestaurant);
        restaurantDAO.add(otherRestaurant);
        assertEquals(2, restaurantDAO.getAll().size());
    }

    @Test
    public void findByIdCorrectlyFindsARestaurantById() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        restaurantDAO.add(testRestaurant);
        Restaurant foundRestaurant = restaurantDAO.findById(testRestaurant.getId());
        assertEquals(testRestaurant, foundRestaurant);
    }

    @Test
    public void updateCorrectlyUpdatesRestaurantProperties() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        restaurantDAO.add(testRestaurant);

        restaurantDAO.update(testRestaurant.getId(), "Screen Door", "4321 SE Burnside", "97322","503-876-5309", "wwww.screendoor.com","screendoor@email.com");
        Restaurant updatedRestaurant = restaurantDAO.findById(testRestaurant.getId());
        assertNotEquals(testRestaurant, updatedRestaurant);
    }

    @Test
    public void deleteByIdDeletesCorrectRestaurant() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        restaurantDAO.add(testRestaurant);
        restaurantDAO.deleteById(testRestaurant.getId());
        assertEquals(0, restaurantDAO.getAll().size());
    }

    @Test
    public void avgRatingForARestaurantIsCorrectlyCalc() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        restaurantDAO.add(testRestaurant);

        int testRestaurantId = testRestaurant.getId();

        Review testReview2 = new Review("Ronald McDonald", "Adequate appetizers!", 75, testRestaurant.getId());
        reviewDAO.add(testReview2);

        Review testReview1 = new Review("Wendy", "foodcoma!", 95, testRestaurant.getId());
        reviewDAO.add(testReview1);




        assertEquals(85, restaurantDAO.avgRestaurantRating(testRestaurantId));

    }

}