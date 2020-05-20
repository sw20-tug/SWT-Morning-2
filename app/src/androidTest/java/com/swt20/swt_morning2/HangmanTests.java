package com.swt20.swt_morning2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import junit.framework.AssertionFailedError;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import junit.framework.AssertionFailedError;

import static androidx.test.espresso.Espresso.onView;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.swt20.swt_morning2.HangmanTests.EspressoTestsMatchers.withDrawable;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;


@RunWith(AndroidJUnit4.class)
public class HangmanTests {

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void playGame() {
        Activity activity = activityRule.getActivity();
        ScoreTracker st = new ScoreTracker(activity.getApplicationContext());
        Integer old_score = st.getScore(Game.HANGMAN);
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
                try {
                    onView(withId(R.id.textView_word2guess)).check(matches(withText(containsString("_"))));
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {

                    }
                    assertThat(st.getScore(Game.HANGMAN), is(old_score - 2));
                    return;
                } catch (AssertionFailedError e) {
                    // View not displayed
                }

                // View displayed
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {

                }
                assertThat(st.getScore(Game.HANGMAN), is(old_score + 1));
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
        assertThat(false, is(true));
    }

    @Test
    public void pressWrongLetterTwice() {

        // Go from Main Menu to Hangman Menu
        onView(withId(R.id.hangmanButton)).perform(click());

        // Go from Hangman Menu to Game
        onView(withId(R.id.ttt_menu_button)).perform(click());

        String letter = "y";

        for (int i = 0; i < 2; i++) {
            onView(withId(R.id.plainText_nextChar)).perform(typeText(letter));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            try {
                onView(withId(R.id.feedbackView)).check(matches(withDrawable(R.drawable.hangman_1)));
                return;
                // View displayed
            } catch (AssertionFailedError e) {
                // View not displayed
            }
        }
        assertThat(false, is(true));
    }

    @Test
    public void pressLetterNineTimes() {

        // Go from Main Menu to Hangman Menu
        onView(withId(R.id.hangmanButton)).perform(click());

        // Go from Hangman Menu to Game
        onView(withId(R.id.ttt_menu_button)).perform(click());

        String letter = "a";

        for (int i = 0; i < 10; i++) {
            onView(withId(R.id.plainText_nextChar)).perform(typeText(letter));
            try {

                onView(withId(R.id.button_playagain)).check(matches(isDisplayed()));
                return;
                // View displayed
            } catch (AssertionFailedError e) {
                // View not displayed
            }
        }
        assertThat(false, is(true));
    }

    @Test
    public void scoreReduced() {
        Activity activity = activityRule.getActivity();
        ScoreTracker st = new ScoreTracker(activity.getApplicationContext());
        int old_score = st.getScore(Game.HANGMAN);
        // Go from Main Menu to Hangman Menu
        onView(withId(R.id.hangmanButton)).perform(click());

        // Go from Hangman Menu to Game
        onView(withId(R.id.ttt_menu_button)).perform(click());
        String letter = "a";
        for (int i = 0; i <= 9; i++) {
            onView(withId(R.id.plainText_nextChar)).perform(typeText(letter));
            try {
                onView(withId(R.id.button_playagain)).check(matches(isDisplayed()));
                onView(withId(R.id.button_playagain)).perform(click());
                onView(isRoot()).perform(pressBack());
                onView(isRoot()).perform(pressBack());
                // View displayed
                break;
            } catch (AssertionFailedError e) {
                // View not displayed
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
        ScoreTracker st2 = new ScoreTracker(activity.getApplicationContext());
        assertThat(st2.getScore(Game.HANGMAN), is(old_score - 2));
        //assertThat(false,is(true));
    }

    public static class EspressoTestsMatchers {

        public static Matcher<View> withDrawable(final int resourceId) {
            return new DrawableMatcher(resourceId);
        }

        public static Matcher<View> noDrawable() {
            return new DrawableMatcher(-1);
        }
    }

}
