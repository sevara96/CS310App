package com.example.cs310app;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnitRunner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;

import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;





public class PersonProfileActivityTest extends AndroidJUnitRunner {


    @Rule
    public ActivityTestRule<PersonProfileActivity> mProfilePageActivity =  new ActivityTestRule<>(PersonProfileActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testEmail() {

        onView(withId(R.id.person_email)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void testPhone() {

        onView(withId(R.id.person_phone)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public  void testName(){

        onView(withId(R.id.person_fullName)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    public void declineButton(){
        onView(withId(R.id.person_decline_friend_req)).check(matches((isClickable())));

    }

    @Test
    public void test() {

        onView(withId(R.id.person_send_friend_req)).check(matches((isClickable())));

    }

    @After
    public void tearDown() throws Exception {


    }
}