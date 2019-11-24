//package com.example.cs310app;
//
//
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
//@LargeTest
//@RunWith(AndroidJUnit4.class)
//public class Main_Activity_Test {
//
//    @Rule
//    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
//
//    @Test
//    public void main_Activity_Test() {
//        ViewInteraction appCompatEditText = onView(
//                allOf(withId(R.id.username),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        appCompatEditText.perform(replaceText("annausu.e"), closeSoftKeyboard());
//
//        ViewInteraction appCompatEditText2 = onView(
//                allOf(withId(R.id.username), withText("annausu.e"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        appCompatEditText2.perform(click());
//
//        ViewInteraction appCompatEditText3 = onView(
//                allOf(withId(R.id.username), withText("annausu.e"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        appCompatEditText3.perform(replaceText("annaser"));
//
//        ViewInteraction appCompatEditText4 = onView(
//                allOf(withId(R.id.username), withText("annaser"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        appCompatEditText4.perform(closeSoftKeyboard());
//
//        ViewInteraction appCompatEditText5 = onView(
//                allOf(withId(R.id.username), withText("annaser"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        appCompatEditText5.perform(click());
//
//        ViewInteraction appCompatEditText6 = onView(
//                allOf(withId(R.id.username), withText("annaser"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        appCompatEditText6.perform(replaceText("annauser"));
//
//        ViewInteraction appCompatEditText7 = onView(
//                allOf(withId(R.id.username), withText("annauser"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        appCompatEditText7.perform(closeSoftKeyboard());
//
//        ViewInteraction appCompatEditText8 = onView(
//                allOf(withId(R.id.username), withText("annauser"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        appCompatEditText8.perform(click());
//
//        ViewInteraction appCompatEditText9 = onView(
//                allOf(withId(R.id.username), withText("annauser"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        appCompatEditText9.perform(replaceText("annauser@usc.edu"));
//
//        ViewInteraction appCompatEditText10 = onView(
//                allOf(withId(R.id.username), withText("annauser@usc.edu"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        appCompatEditText10.perform(closeSoftKeyboard());
//
//        ViewInteraction appCompatEditText11 = onView(
//                allOf(withId(R.id.password),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                2),
//                        isDisplayed()));
//        appCompatEditText11.perform(replaceText("password"), closeSoftKeyboard());
//
//        ViewInteraction appCompatButton = onView(
//                allOf(withId(R.id.login), withText("Login"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                3),
//                        isDisplayed()));
//        appCompatButton.perform(click());
//
//        ViewInteraction relativeLayout = onView(
//                allOf(childAtPosition(
//                        childAtPosition(
//                                withId(R.id.myrecyclerView),
//                                0),
//                        0),
//                        isDisplayed()));
//        relativeLayout.check(matches(isDisplayed()));
//
//        ViewInteraction relativeLayout2 = onView(
//                allOf(childAtPosition(
//                        childAtPosition(
//                                withId(R.id.myrecyclerView),
//                                1),
//                        0),
//                        isDisplayed()));
//        relativeLayout2.check(matches(isDisplayed()));
//
//        ViewInteraction imageView = onView(
//                allOf(withContentDescription("More options"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.toolbar),
//                                        1),
//                                1),
//                        isDisplayed()));
//        imageView.check(matches(isDisplayed()));
//
//        ViewInteraction textView = onView(
//                allOf(withId(R.id.search), withContentDescription("Search"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.toolbar),
//                                        1),
//                                0),
//                        isDisplayed()));
//        textView.check(matches(withText("cat")));
//
//        ViewInteraction textView2 = onView(
//                allOf(withId(R.id.search), withContentDescription("Search"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.toolbar),
//                                        1),
//                                0),
//                        isDisplayed()));
//        textView2.check(matches(withText("cat")));
//    }
//
//    private static Matcher<View> childAtPosition(
//            final Matcher<View> parentMatcher, final int position) {
//
//        return new TypeSafeMatcher<View>() {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("Child at position " + position + " in parent ");
//                parentMatcher.describeTo(description);
//            }
//
//            @Override
//            public boolean matchesSafely(View view) {
//                ViewParent parent = view.getParent();
//                return parent instanceof ViewGroup && parentMatcher.matches(parent)
//                        && view.equals(((ViewGroup) parent).getChildAt(position));
//            }
//        };
//    }
//}
