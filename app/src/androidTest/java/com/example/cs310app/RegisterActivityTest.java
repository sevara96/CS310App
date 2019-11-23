package com.example.cs310app;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> registerActivityTestRule = new ActivityTestRule<RegisterActivity>(RegisterActivity.class);

    private String notUSCEmail = "test@gmail.com";
    private String USCEmail = "test@usc.edu";
    private String password = "password";
    private String confirmPassword = "password";
    private String wrongConfirmPassword = "notpassword";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testMissingEmail() {
        //Input password into edit text
        Espresso.onView(withId(R.id.password)).perform(typeText(password));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Input password into edit text
        Espresso.onView(withId(R.id.confirmPassword)).perform(typeText(confirmPassword));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Perform login button click
        Espresso.onView(withId(R.id.register)).perform(click());
        //Test if Toast text is displayed
        Espresso.onView(withText("Please write your email...")).inRoot(withDecorView(not(registerActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }
    @Test
    public void testMissingPassword() {
        //Input password into edit text
        Espresso.onView(withId(R.id.username)).perform(typeText(USCEmail));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Input password into edit text
        Espresso.onView(withId(R.id.confirmPassword)).perform(typeText(confirmPassword));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Perform login button click
        Espresso.onView(withId(R.id.register)).perform(click());
        //Test if Toast text is displayed
        Espresso.onView(withText("Please write your password...")).inRoot(withDecorView(not(registerActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }
    @Test
    public void testMissingConfirmPassword() {
        //Input email into edit text
        Espresso.onView(withId(R.id.username)).perform(typeText(USCEmail));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Input password into edit text
        Espresso.onView(withId(R.id.password)).perform(typeText(password));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Perform login button click
        Espresso.onView(withId(R.id.register)).perform(click());
        //Test if Toast text is displayed
        Espresso.onView(withText("Please confirm your password...")).inRoot(withDecorView(not(registerActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }
    @Test
    public void testWrongConfirmPassword() {
        //Input email into edit text
        Espresso.onView(withId(R.id.username)).perform(typeText(USCEmail));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Input password into edit text
        Espresso.onView(withId(R.id.password)).perform(typeText(password));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Input confirm password into edit text
        Espresso.onView(withId(R.id.confirmPassword)).perform(typeText(wrongConfirmPassword));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Perform login button click
        Espresso.onView(withId(R.id.register)).perform(click());
        //Test if Toast text is displayed
        Espresso.onView(withText("Passwords don't match...")).inRoot(withDecorView(not(registerActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }
    @Test
    public void testNonUSCEmail() {
        //Input incorrect email into edit text
        Espresso.onView(withId(R.id.username)).perform(typeText(notUSCEmail));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Input password into edit text
        Espresso.onView(withId(R.id.password)).perform(typeText(password));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Input confirm password into edit text
        Espresso.onView(withId(R.id.confirmPassword)).perform(typeText(confirmPassword));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Perform login button click
        Espresso.onView(withId(R.id.register)).perform(click());
        //Test if Toast text is displayed
        Espresso.onView(withText("Please use a USC email to sign up...")).inRoot(withDecorView(not(registerActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
    }
}