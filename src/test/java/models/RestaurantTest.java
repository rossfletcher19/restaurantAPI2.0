package models;

import enums.DiningStyle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RestaurantTest {



    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    //getter tests

    @Test
    public void getNameReturnsCorrectName() throws Exception {
       Restaurant testRestaurant = setupRestaurant();
       assertEquals("Fish Witch", testRestaurant.getName());
    }

    @Test
    public void getAddressReturnsCorrectAddress() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        assertEquals("214 NE Broadway", testRestaurant.getAddress());
    }

    @Test
    public void getZipReturnsCorrectZip() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        assertEquals("97232", testRestaurant.getZipcode());
    }
    @Test
    public void getPhoneReturnsCorrectPhone() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        assertEquals("503-402-9874", testRestaurant.getPhone());
    }

    @Test
    public void getWebsiteReturnsCorrectWebsite() throws Exception {
        Restaurant testRestaurant = setupAltRestaurant();
        assertEquals("no website listed", testRestaurant.getWebsite());
    }

    @Test
    public void getEmailReturnsCorrectEmail() throws Exception {
        Restaurant testRestaurant = setupAltRestaurant();
        assertEquals("no email available", testRestaurant.getEmail());
    }

    @Test
    public void getImageReturnsCorrectImage() throws Exception {
        Restaurant testRestaurant = setupAltRestaurant();
        assertEquals("/resources/images/uploads/no_image.jpg", testRestaurant.getImage());
    }

    @Test
    public void getDiningStylereturnsCorrectPhrase() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        assertEquals("Fish Witch likes to keep things pretty casual. No stuffy suits here!", testRestaurant.getDiningStyle());
    }

    //setter tests

    @Test
    public void setNameSetsCorrectName() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setName("Steak House");
        assertNotEquals("Fish Witch",testRestaurant.getName());
    }

    @Test
    public void setAddressSetsCorrectAddress() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setAddress("6600 NE Ainsworth");
        assertNotEquals("214 NE Broadway", testRestaurant.getAddress());
    }

    @Test
    public void setZipSetsCorrectZip() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setZipcode("78902");
        assertNotEquals("97232", testRestaurant.getZipcode());
    }
    @Test
    public void setPhoneSetsCorrectPhone() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setPhone("971-898-7878");
        assertNotEquals("503-402-9874", testRestaurant.getPhone());
    }

    @Test
    public void setWebsiteSetsCorrectWebsite() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setWebsite("http://steakhouse.com");
        assertNotEquals("http://fishwitch.com", testRestaurant.getWebsite());
    }

    @Test
    public void setEmailSetsCorrectEmail() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setEmail("steak@steakhouse.com");
        assertNotEquals("hellofishy@fishwitch.com", testRestaurant.getEmail());
    }

    @Test
    public void setImageSetsCorrectImage() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        testRestaurant.setImage("steakhouse.jpg");
        assertNotEquals("fishwitch.jpg", testRestaurant.getImage());
    }

    //helpers

    public Restaurant setupRestaurant (){
        return new Restaurant("Fish Witch", "214 NE Broadway", "97232", "503-402-9874", "http://fishwitch.com", "hellofishy@fishwitch.com", "fishwitch.jpg", DiningStyle.CASUAL );

    }

    public Restaurant setupAltRestaurant (){
        return new Restaurant("Fish Witch", "214 NE Broadway", "97232", "503-402-9874", DiningStyle.CASUAL);

    }

}