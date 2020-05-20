package com.swt20.swt_morning2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import junit.framework.AssertionFailedError;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class HangmanTests {

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void playGame() {
        Activity activity = activityRule.getActivity();
        ScoreTracker st = new ScoreTracker(activity.getApplicationContext());
        int oldScore = st.getScore(Game.HANGMAN);
        // Go from Main Menu to Hangman Menu
        onView(withId(R.id.hangmanButton)).perform(click());

        // Go from Hangman Menu to Game
        onView(withId(R.id.ttt_menu_button)).perform(click());
        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        for (String str : letters) {
            onView(withId(R.id.plainText_nextChar)).perform(typeText(str));
            try {
                onView(withId(R.id.button_playagain)).check(matches(isDisplayed()));
                // View displayed
                assert (st.getScore(Game.HANGMAN) == oldScore + 1);
                return;
            } catch (AssertionFailedError e) {
                // View not displayed
            }
        }
        assert (false);
    }

    @Test
    public void playGameTwice() {

        // Go from Main Menu to Hangman Menu
        onView(withId(R.id.hangmanButton)).perform(click());

        // Go from Hangman Menu to Game
        onView(withId(R.id.ttt_menu_button)).perform(click());

        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        for (int i = 0; i < 2; i++) {
            for (String str : letters) {
                onView(withId(R.id.plainText_nextChar)).perform(typeText(str));
                try {
                    onView(withId(R.id.button_playagain)).check(matches(isDisplayed()));
                    // View displayed
                    if (i < 1) {
                        onView(withId(R.id.button_playagain)).perform(click());
                    } else {
                        return;
                    }
                } catch (AssertionFailedError e) {
                    // View not displayed
                }
            }
        }
        assert (false);
    }
}
