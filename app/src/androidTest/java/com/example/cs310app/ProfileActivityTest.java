package com.example.cs310app;

import android.widget.TextView;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.core.StringContains.containsString;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class ProfileActivityTest {

    @Rule
    public ActivityTestRule<ProfileActivity> mProfileActivityTestRule =  new ActivityTestRule<>(ProfileActivity.class);

    private String mName="JM";
    private String email="jm@usc.edu";
    private String phoneNumber="777-7777";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testEmail(){
        onView(withId(R.id.email))        // withId(R.id.my_view) is a ViewMatcher
                .perform(click())               // click() is a ViewAction
                .check(matches(isDisplayed()));
    }

    @Test
    public void testName(){
        onView(withId(R.id.fullName))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    @Test
    public void testPhoneNumber(){
        onView(withId(R.id.phone))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
    }
}
