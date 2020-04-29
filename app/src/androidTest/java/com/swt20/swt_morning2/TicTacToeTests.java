package com.swt20.swt_morning2;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class TicTacToeTests {

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void playTheGameABit() {
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
                }
                // Go back to Tic-Tac-Toe Menu
                Espresso.pressBackUnconditionally();
            }
            // Go back to Main Menu
            Espresso.pressBackUnconditionally();
        }
    }

    @Test
    public void playToWin() {
        onView(withId(R.id.ticTacToeButton)).perform(click());
        onView(withId(R.id.ttt_menu_button)).perform(click());
        onView(withId(R.id.imageView)).perform(click());
        onView(withId(R.id.imageView2)).perform(click());
        onView(withId(R.id.imageView3)).perform(click());
        onView(withId(R.id.imageView4)).perform(click());
        onView(withId(R.id.imageView5)).perform(click());
        onView(withId(R.id.imageView6)).perform(click());
        onView(withId(R.id.imageView7)).perform(click());
        onView(withId(R.id.ttt_menu_button)).check(matches(isDisplayed()));
        onView(withText(R.string.you_win)).inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        Espresso.pressBackUnconditionally();
    }

    @Test
    public void playToLose() {
        onView(withId(R.id.ticTacToeButton)).perform(click());
        onView(withId(R.id.ttt_menu_button)).perform(click());
        onView(withId(R.id.imageView)).perform(click());
        onView(withId(R.id.imageView3)).perform(click());
        onView(withId(R.id.imageView5)).perform(click());
        onView(withId(R.id.imageView4)).perform(click());
        onView(withId(R.id.imageView9)).perform(click());
        onView(withId(R.id.imageView8)).perform(click());
        onView(withId(R.id.ttt_menu_button)).check(matches(isDisplayed()));
        onView(withText(R.string.you_lose)).inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        Espresso.pressBackUnconditionally();
    }

    @Test
    public void checkPointsWhenWinning() {
        onView(withId(R.id.ticTacToeButton)).perform(click());
        onView(withId(R.id.ttt_menu_button)).perform(click());
        onView(withId(R.id.imageView)).perform(click());
        onView(withId(R.id.imageView2)).perform(click());
        onView(withId(R.id.imageView3)).perform(click());
        onView(withId(R.id.imageView4)).perform(click());
        onView(withId(R.id.imageView5)).perform(click());
        onView(withId(R.id.imageView6)).perform(click());
        onView(withId(R.id.imageView7)).perform(click());
        onView(withId(R.id.ttt_menu_button)).check(matches(isDisplayed()));
        onView(withText(R.string.you_win)).inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        Espresso.pressBackUnconditionally();
    }


}
