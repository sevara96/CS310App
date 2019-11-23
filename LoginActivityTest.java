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

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);



    private String incorrectEmail = "incorrect@usc.edu";
    private String incorrectPassword = "notapassword";
    private String correctEmail = "ajhutchi@usc.edu";
    private String correctPassword = "password";
    private String errorMessage = "Error occurred: There is no user record corresponding to this identifier. The user may have been deleted.";
    private String passwordErrorMessage = "Error occurred: The password is invalid or the user does not have a password.";
    private String successMessage = "You are logged in";


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testMissingPassword() {
        //Input email into edit text
        Espresso.onView(withId(R.id.username)).perform(typeText(correctEmail));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Perform login button click
        Espresso.onView(withId(R.id.login)).perform(click());
        //Test if Toast text is correct
        Espresso.onView(withText("Please write your password...")).inRoot(withDecorView(not(loginActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testMissingEmail() {
        //Input password into edit text
        Espresso.onView(withId(R.id.password)).perform(typeText(correctPassword));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Perform login button click
        Espresso.onView(withId(R.id.login)).perform(click());
        //Test if Toast text is displayed
        Espresso.onView(withText("Please write your email...")).inRoot(withDecorView(not(loginActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testIncorrectEmail() {
        //Input incorrect email into edit text
        Espresso.onView(withId(R.id.username)).perform(typeText(incorrectEmail));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Input password into edit text
        Espresso.onView(withId(R.id.password)).perform(typeText(correctPassword));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Perform login button click
        Espresso.onView(withId(R.id.login)).perform(click());
        //Test if Toast text is displayed
        Espresso.onView(withText(errorMessage)).inRoot(withDecorView(not(loginActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testIncorrectPassword() {
        //Input incorrect email into edit text
        Espresso.onView(withId(R.id.username)).perform(typeText(correctEmail));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Input password into edit text
        Espresso.onView(withId(R.id.password)).perform(typeText(incorrectPassword));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Perform login button click
        Espresso.onView(withId(R.id.login)).perform(click());
        //Test if Toast text is displayed
        Espresso.onView(withText(passwordErrorMessage)).inRoot(withDecorView(not(loginActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testCorrectLogin() {
        //Input incorrect email into edit text
        Espresso.onView(withId(R.id.username)).perform(typeText(correctEmail));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Input password into edit text
        Espresso.onView(withId(R.id.password)).perform(typeText(correctPassword));
        //Close software keyboard
        Espresso.closeSoftKeyboard();
        //Perform login button click
        Espresso.onView(withId(R.id.login)).perform(click());


        Espresso.onView(withId(R.id.toprofilebutton)).check(matches(isDisplayed()));

        //Test if Toast text is displayed
        //Espresso.onView(withText(successMessage)).inRoot(withDecorView(not(mainActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        //loginActivityTestRule.getActivity().finish();
        //assertTrue(loginActivityTestRule.getActivity().isFinishing());
    }


    @After
    public void tearDown() throws Exception {

    }
}