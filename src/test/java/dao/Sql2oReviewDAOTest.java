package dao;

import models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Sql2oReviewDAOTest {

    private Connection conn;
    private Sql2oReviewDAO reviewDAO;
    private Sql2oRestaurantDAO restaurantDAO;

    public Review setupNewReview() {
        return new Review("Ross F.", "Great Southern Food!", 5, 1);
    }

    public Restaurant setupRestaurant (){
        return new Restaurant("Screen Door", "1234 SE Burnside", "97232", "503-876-5309", "http://screendoor.com", "screendoor@email.com");
    }

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "","");
        reviewDAO = new Sql2oReviewDAO(sql2o);
        restaurantDAO = new Sql2oRestaurantDAO(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test // adding review sets id
    public void addCorrectlyAddsAReview() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        restaurantDAO.add(testRestaurant);
        Review testReview = new Review("Ross F.","Great Food!",5, testRestaurant.getId());
        reviewDAO.add(testReview);
        assertEquals(1, testReview.getId());
    }

    @Test
    public void getAllReviewsByRestaurant() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        restaurantDAO.add(testRestaurant);

        Restaurant otherRestaurant = setupRestaurant();
        restaurantDAO.add(otherRestaurant);

        Review testReview = new Review("Ross F.","Great Food!",5, testRestaurant.getId());
        reviewDAO.add(testReview);

        Review otherReview = new Review("Mr. Spock","Passable",1, testRestaurant.getId());
        reviewDAO.add(otherReview);

        assertEquals(2, reviewDAO.getAllReviewsByRestaurant(testRestaurant.getId()).size());
        assertEquals(0, reviewDAO.getAllReviewsByRestaurant(otherRestaurant.getId()).size());
    }

    @Test
    public void getAllReturnsAllReviews() throws Exception {
    Restaurant testRestaurant = setupRestaurant();
    restaurantDAO.add(testRestaurant);

    Review testReview = new Review("Captain Kirk","food coma!",3, testRestaurant.getId());
    reviewDAO.add(testReview);

    Review otherReview = new Review("Mr. Spock","Passable",1, testRestaurant.getId());
    reviewDAO.add(otherReview);

    Review reviewFromDatabase = reviewDAO.getAll().get(0);

    assertEquals(2, reviewDAO.getAll().size());
    assertArrayEquals(new Object[]{"Captain Kirk", "food coma!",3, 1}, new Object[]{reviewFromDatabase.getWrittenBy(), reviewFromDatabase.getContent(), reviewFromDatabase.getRating(),  reviewFromDatabase.getId()});
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
        assertEquals(85, reviewDAO.avgRestaurantRating(testRestaurantId));
    }

    @Test
    public void deleteByIdDeletesAReviewByIdCorrectly() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        restaurantDAO.add(testRestaurant);
        Review testReview = new Review("Captain Kirk","food coma!",3, testRestaurant.getId());
        reviewDAO.add(testReview);
        reviewDAO.deleteById(testReview.getId());
        assertEquals(0, reviewDAO.getAllReviewsByRestaurant(testRestaurant.getId()).size());
    }

}