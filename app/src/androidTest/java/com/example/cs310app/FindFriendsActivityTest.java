package com.example.cs310app;

import androidx.test.espresso.action.TypeTextAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;

import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withInputType;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class FindFriendsActivityTest {

    @Rule
    public ActivityTestRule<FindFriendsActivity> activityRule
            = new ActivityTestRule<>(FindFriendsActivity.class);


    @Test
    public void testHintVisibility(){
        // check hint visibility
        onView(withId(R.id.inputField)).check(matches(withHint("search here..")));
        // enter name
        onView(withId(R.id.inputField)).perform(typeText("Hello"));
        onView(withId(R.id.inputField)).check(matches(withText("Hello")));
    }

    @Test
    public void isRecyclerVisible(){
        onView(withId(R.id.results)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }





}