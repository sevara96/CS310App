package com.example.cs310app;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class findFriendsUnitTest {

    findFriends ff = null;
    String profileImage;
    String fullName;

    @Before
    public void setUp(){
        profileImage = "www.image.com";
        fullName = "Jeff Millz";
        ff = new findFriends(profileImage, fullName);
    }

    @After
    public void tearDown(){
        ff = null;
    }

    @Test
    public void testConstructor() {
        assertTrue(profileImage.equals(ff.profileImage));
        assertTrue(fullName.equals(ff.fullName));
    }

    @Test
    public void testgetProfileImage() {
        assertTrue(profileImage.equals(ff.getProfileImage()));
    }

    @Test
    public void testgetFullName() {
        assertTrue(fullName.equals(ff.getFullName()));
    }

    @Test
    public void testsetProfileImage() {

        ff.profileImage = "www.newimage.com";
        String newpic = ff.profileImage;
        assertTrue(newpic.equals(ff.profileImage));
    }

    @Test
    public void testsetFullName() {

        ff.fullName = "C Wang";
        String newname = ff.fullName;
        assertTrue(newname.equals(ff.fullName));
    }

}

