package com.swt20.swt_morning2;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class TicTacToeTests {

    /* TODO we have to delete this for findBugs, since it is not used
    but we did not wrote that code, so we are not sure if it is ok to remove it.
    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);
    */

    @Test
    public void playGame() {

        for (int a = 0; a < 2; a++) {

            // Go from Main Menu to Tic-Tac-Toe Menu
            onView(withId(R.id.ticTacToeButton)).perform(click());

            for (int b = 0; b < 2; b++) {

                // Go from TicTacToe Menu to Game
                onView(withId(R.id.ttt_menu_button)).perform(click());

                // Play
                for (int c = 0; c < 2; c++) {
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

                // Go back to Tic-Tac-Toe Menu
                onView(withId(R.id.ttt_game_button)).perform(click());
            }

            // Go back to Main Menu
            onView(withId(R.id.ttt_menu_back_button)).perform(click());
        }
    }


}
