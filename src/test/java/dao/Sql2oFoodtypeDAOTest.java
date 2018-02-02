package dao;

import models.Foodtype;
import models.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oFoodtypeDAOTest {

    private org.sql2o.Connection conn;
    private Sql2oRestaurantDAO restaurantDAO;
    private Sql2oFoodtypeDAO foodtypeDAO;

    public Foodtype setupNewFoodtype() {
        return new Foodtype("BBQ");
    }

    public Restaurant setupRestaurant (){
        return new Restaurant("Screen Door", "1234 SE Burnside", "97232", "503-876-5309", "http://screendoor.com", "screendoor@email.com");
    }

    public Restaurant setupAltRestaurant (){
        return new Restaurant("Screen Door", "1234 SE Burnside", "97232", "503-876-5309");
    }

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        foodtypeDAO = new Sql2oFoodtypeDAO(sql2o);
        restaurantDAO = new Sql2oRestaurantDAO(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addSetsIdAndAddsToFoodtype() throws Exception {
        Foodtype testFoodtype = setupNewFoodtype();
        foodtypeDAO.add(testFoodtype);
        assertEquals(1, testFoodtype.getId());
    }

    @Test
    public void getAllRetrievesAllFoodtypes() throws Exception {
        Foodtype foodtype = setupNewFoodtype();
        foodtypeDAO.add(foodtype);

        assertEquals(1, foodtypeDAO.getAll().size());
    }

    @Test
    public void deleteByIdDeletesAFoodtypeByIdCorrectly() throws Exception {
        Foodtype foodtype = setupNewFoodtype();
        foodtypeDAO.add(foodtype);
        foodtypeDAO.deleteById(foodtype.getId());
        assertEquals(0, foodtypeDAO.getAll().size());
    }

    @Test
    public void addFoodtypeToRestaurantAddsTypeCorrectly() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        Restaurant altRestaurant = setupAltRestaurant();

        restaurantDAO.add(testRestaurant);
        restaurantDAO.add(altRestaurant);

        Foodtype testFoodtype = setupNewFoodtype();

        foodtypeDAO.add(testFoodtype);

        foodtypeDAO.addFoodTypeToRestaurant(testFoodtype, testRestaurant);
        foodtypeDAO.addFoodTypeToRestaurant(testFoodtype, altRestaurant);
        assertEquals(2, foodtypeDAO.getAllRestaurantsForAFoodtype(testFoodtype.getId()).size());
    }

    @Test
    public void deleteingRestaurantAlseUpdatesJoinTable() throws Exception {
        Foodtype testFoodtype = new Foodtype("Italian");
        foodtypeDAO.add(testFoodtype);

        Restaurant testRestaurant = setupRestaurant();
        restaurantDAO.add(testRestaurant);

        Restaurant altRestaurant = setupAltRestaurant();
        restaurantDAO.add(altRestaurant);

        restaurantDAO.addRestaurantToFoodType(testRestaurant, testFoodtype);
        restaurantDAO.addRestaurantToFoodType(altRestaurant, testFoodtype);

        restaurantDAO.deleteById(testRestaurant.getId());
        assertEquals(0, restaurantDAO.getAllFoodtypesForARestaurant(testRestaurant.getId()).size());
    }

    @Test
    public void getAllRestaurantsForAFoodtype() throws Exception {
        Foodtype foodtype1 = new Foodtype("Ramen");
        foodtypeDAO.add(foodtype1);

        Restaurant restaurant1 = setupRestaurant();
        restaurantDAO.add(restaurant1);
        foodtypeDAO.addFoodTypeToRestaurant(foodtype1, restaurant1);

        Restaurant restaurant2 = setupAltRestaurant();
        restaurantDAO.add(restaurant2);
        foodtypeDAO.addFoodTypeToRestaurant(foodtype1, restaurant2);

        assertEquals(4, foodtypeDAO.getAllRestaurantsForAFoodtype(foodtype1.getId()).size());
    }

}