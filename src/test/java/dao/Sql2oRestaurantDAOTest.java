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
        return new Restaurant("Fish Witch", "214 NE Broadway", "97232", "503-402-9874", "http://fishwitch.com", "hellofishy@fishwitch.com");
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
    public void getAll() throws Exception {
    }

    @Test
    public void findById() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void deleteById() throws Exception {
    }

}