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
import static org.hamcrest.core.IsNot.not;

public class SellActivityTest {

    @Rule
    public ActivityTestRule<SellActivity> mSellActivityTestRule =  new ActivityTestRule<>(SellActivity.class);

    private String itemName="testItem";
    private String imageURL="item.com";
    private String description="item description";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testOnlyItemName()
    {
        //input some text in item name edit box
        Espresso.onView(withId(R.id.itemNameEditText)).perform(typeText(itemName));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        Espresso.onView(withId(R.id.saleButton)).perform(click());
        //checking the text in the textview
        Espresso.onView(withText("You must enter image of the item to sell")).inRoot(withDecorView(not(mSellActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

//    @Test
//    public void testOnlyItemImageURL()
//    {
//        //input some text in item image url edit box
//        Espresso.onView(withId(R.id.URLeditText)).perform(typeText(imageURL));
//        //close soft keyboard
//        Espresso.closeSoftKeyboard();
//        //perform button click
//        Espresso.onView(withId(R.id.saleButton)).perform(click());
//        //checking the text in the textview
//        Espresso.onView(withText("You must enter name of the item to sell")).inRoot(withDecorView(not(mSellActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
//    }

    @Test
    public void testOnlyItemDescription()
    {
        //input some text in item description edit box
        Espresso.onView(withId(R.id.descEditText)).perform(typeText(description));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        Espresso.onView(withId(R.id.saleButton)).perform(click());
        //checking the text in the textview
        Espresso.onView(withText("You must enter name of the item to sell")).inRoot(withDecorView(not(mSellActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testEmpty()
    {
        //perform button click
        Espresso.onView(withId(R.id.saleButton)).perform(click());
        //checking the text in the textview
        Espresso.onView(withText("You must enter name of the item to sell")).inRoot(withDecorView(not(mSellActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testNoItemDescription()
    {
        //input some text in item name edit box
        Espresso.onView(withId(R.id.itemNameEditText)).perform(typeText(itemName));
        //input some text in item image url edit box
        //Espresso.onView(withId(R.id.URLeditText)).perform(typeText(imageURL));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        Espresso.onView(withId(R.id.saleButton)).perform(click());
        //checking the text in the textview
        Espresso.onView(withText("You must enter description of the item to sell")).inRoot(withDecorView(not(mSellActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testNoItemURL()
    {
        //input some text in item name edit box
        Espresso.onView(withId(R.id.itemNameEditText)).perform(typeText(itemName));
        //input some text in item description edit box
        Espresso.onView(withId(R.id.descEditText)).perform(typeText(description));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        Espresso.onView(withId(R.id.saleButton)).perform(click());
        //checking the text in the textview
        Espresso.onView(withText("You must enter image of the item to sell")).inRoot(withDecorView(not(mSellActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testNoItemName()
    {
        //input some text in item url edit box
        //Espresso.onView(withId(R.id.URLeditText)).perform(typeText(imageURL));
        //input some text in item description edit box
        Espresso.onView(withId(R.id.descEditText)).perform(typeText(description));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        Espresso.onView(withId(R.id.saleButton)).perform(click());
        //checking the text in the textview
        Espresso.onView(withText("You must enter name of the item to sell")).inRoot(withDecorView(not(mSellActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
    }
}