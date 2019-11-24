//package com.example.cs310app;
//
//import androidx.test.espresso.ViewInteraction;
//import androidx.test.rule.ActivityTestRule;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static org.hamcrest.Matchers.allOf;
//import static org.junit.Assert.*;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//
//import androidx.test.espresso.ViewInteraction;
//import androidx.test.filters.LargeTest;
//import androidx.test.rule.ActivityTestRule;
//import androidx.test.runner.AndroidJUnit4;
//
//import org.hamcrest.Description;
//import org.hamcrest.Matcher;
//import org.hamcrest.TypeSafeMatcher;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static androidx.test.espresso.action.ViewActions.replaceText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.Matchers.allOf;
//
//public class MainActivityTest {
//
//    @Rule
//    public ActivityTestRule<MainActivity> mMainActivityRule =  new ActivityTestRule<>(MainActivity.class);
//
//
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @Test
//    public void testItemName()
//    {
//        onView(withId(R.id.title))
//                .perform(click())
//                .check(matches(isDisplayed()));
//
//        ViewInteraction relativeLayout = onView(
//                allOf(childAtPosition(
//                        childAtPosition(
//                                withId(R.id.myrecyclerView), 0 ),
//                        0),
//                        isDisplayed()));
//        relativeLayout.check(matches(isDisplayed()));
//
//        ViewInteraction relativeLayout2 = onView(
//                allOf(childAtPosition(childAtPosition(withId(R.id.myrecyclerView), 0), 0), isDisplayed()));
//        relativeLayout.check(matches(isDisplayed()));
//        ;
//    }
//
//
//    @After
//    public void tearDown() throws Exception {
//    }
//}