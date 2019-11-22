package com.example.cs310app;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class ItemPageActivityTest {

    @Rule
    public ActivityTestRule<ItemPageActivity> mItemPageActivityRule =  new ActivityTestRule<>(ItemPageActivity.class);
    private String itemName="item name";
    private String description="item description";


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testItemName()
    {
        onView(withId(R.id.title))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    @Test
    public void testItemDescription()
    {
        onView(withId(R.id.description))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
    }
}