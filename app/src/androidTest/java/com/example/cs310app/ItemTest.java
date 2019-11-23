package com.example.cs310app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    String testTitle = "Smart TV";
    String testNewTitle = "NEW Smart TV";
    String testPhotoURL = "filepath";
    String testNewPhotoURL = "NEW_filepath";
    String testDescription = "A 50' Samsung Smart TV";
    String testNewDescription = "A NEW 50' Samsung Smart TV";
    double testPrice = 500.0;
    double testNewPrice = 1000.0;
    String testFullName = "Jeffery Miller";
    String testEmail = "jm@usc.edu";
    String testPhone = "444-123-4455";


    Item testItem = null;

    @Before
    public void setUp() throws Exception {
        testItem = new Item(testTitle, testPhotoURL, testDescription, testPrice, testFullName, testEmail, testPhone);
    }

    @After
    public void tearDown() throws Exception {
        testItem = null;
    }

    @Test
    public void testGetDescription() {
        String description = testItem.getDescription();
        assertTrue(description.equals(testDescription));
    }

    @Test
    public void testGetFullName() {
        String fullName = testItem.getFullName();
        assertTrue(fullName.equals(testFullName));
    }

    @Test
    public void testGetEmail() {
        String email = testItem.getEmail();
        assertTrue(email.equals(testEmail));
    }

    @Test
    public void testGetPhone() {
        String phone = testItem.getPhone();
        assertTrue(phone.equals(testPhone));
    }

    @Test
    public void testGetItemPhotoURL() {
        String itemPhotoURL = testItem.getItemPhotoURL();
        assertTrue(itemPhotoURL.equals(testPhotoURL));
    }

    @Test
    public void testGetTitle() {
        String title = testItem.getTitle();
        assertTrue(title.equals(testTitle));
    }

    @Test
    public void testGetPrice() {
        double price = testItem.getPrice();
        assertTrue(price == testPrice);
    }

    @Test
    public void testGetStringPrice() {
        String stringPrice = testItem.getStringPrice();
        assertTrue(stringPrice.equals(Double.toString(testPrice)));
    }

    @Test
    public void testSetDescription() {
        testItem.setDescription(testNewDescription);
        assertTrue(testItem.getDescription().equals(testNewDescription));
    }

    @Test
    public void testSetItemPhotoURL() {
        testItem.setItemPhotoURL(testNewPhotoURL);
        assertTrue(testItem.getItemPhotoURL().equals(testNewPhotoURL));
    }

    @Test
    public void testSetTitle() {
        testItem.setTitle(testNewTitle);
        assertTrue(testItem.getTitle().equals(testNewTitle));
    }

    @Test
    public void testSetPrice() {
        testItem.setPrice(testNewPrice);
        assertTrue(testItem.getPrice()==testNewPrice);
    }
}