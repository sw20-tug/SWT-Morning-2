package com.swt20.swt_morning2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TicTacToeTests {

    private String stringToBetyped;

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso";
    }

    @Test
    public void doesNotCrash() {
        // Go from TicTacToe Main Menu to Game Menu
        onView(withId(R.id.ttt_menu_button)).perform(click());

        // Play
        for (int i = 0; i < 10; i++)
        {
            onView(withId(R.id.imageView)).perform(click());
            onView(withId(R.id.imageView2)).perform(click());
            onView(withId(R.id.imageView3)).perform(click());
            onView(withId(R.id.imageView4)).perform(click());
            onView(withId(R.id.imageView5)).perform(click());
            onView(withId(R.id.imageView6)).perform(click());
            onView(withId(R.id.imageView7)).perform(click());
            onView(withId(R.id.imageView8)).perform(click());
            onView(withId(R.id.imageView9)).perform(click());
        }

        // Go back to Main Menu
        onView(withId(R.id.ttt_game_button)).perform(click());
    }


}
