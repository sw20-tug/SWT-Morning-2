package com.swt20.swt_morning2;

import android.app.Activity;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
        Integer oldScore = st.getScore(Game.HANGMAN);
        // Go from Main Menu to Hangman Menu
        onView(withId(R.id.hangmanButton)).perform(click());

        // Go from Hangman Menu to Game
        onView(withId(R.id.hangmanStartGameButton)).perform(click());
        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        for (String str : letters) {
            onView(withId(R.id.plainText_nextChar)).perform(typeText(str));
            try {
                onView(withId(R.id.button_playagain)).check(matches(isDisplayed()));
                try {
                    onView(withId(R.id.textView_word2guess)).check(
                            matches(withText(containsString("_"))));
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        // View not displayed
                    }
                    assertThat(st.getScore(Game.HANGMAN), is(oldScore - 2));
                    return;
                } catch (Exception e) {
                    // View not displayed
                }

                // View displayed
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    // View not displayed
                }
                assertThat(st.getScore(Game.HANGMAN), is(oldScore + 1));
                return;
            } catch (AssertionError e) {
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
        onView(withId(R.id.hangmanStartGameButton)).perform(click());

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
                } catch (AssertionError e) {
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
        onView(withId(R.id.hangmanStartGameButton)).perform(click());

        String letter = ".";

        for (int i = 0; i < 2; i++) {
            onView(withId(R.id.plainText_nextChar)).perform(typeText(letter));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // View not displayed
            }
            try {
                onView(withId(R.id.feedbackView)).check(
                        matches(withDrawable(R.drawable.hangman_1)));
                return;
                // View displayed
            } catch (AssertionError e) {
                // View not displayed
            }
        }
        assertThat(false, is(true));
    }

    @Test
    public void pressLetterMoreThanNineTimes() {

        // Go from Main Menu to Hangman Menu
        onView(withId(R.id.hangmanButton)).perform(click());

        // Go from Hangman Menu to Game
        onView(withId(R.id.hangmanStartGameButton)).perform(click());

        String letter = ".";

        for (int i = 0; i < 12; i++) {
            onView(withId(R.id.plainText_nextChar)).perform(typeText(letter));
            try {
                onView(withId(R.id.button_playagain)).check(matches(isDisplayed()));
                return;
                // View displayed
            } catch (AssertionError e) {
                // View not displayed
            }
        }
        assertThat(false, is(true));
    }

    @Test
    public void playGameWithOnlyHints() {
        Activity activity = activityRule.getActivity();
        ScoreTracker st = new ScoreTracker(activity.getApplicationContext());
        Integer oldScore = st.getScore(Game.HANGMAN);
        // Go from Main Menu to Hangman Menu
        onView(withId(R.id.hangmanButton)).perform(click());

        // Go from Hangman Menu to Game
        onView(withId(R.id.hangmanStartGameButton)).perform(click());

        for (int i = 0; i < 20; i++) {
            onView(withId(R.id.button_hangman_hint)).perform(click());
            try {
                onView(withId(R.id.button_playagain)).check(matches(isDisplayed()));
                ScoreTracker finalScore = new ScoreTracker(activity.getApplicationContext());
                assertThat(finalScore.getScore(Game.HANGMAN), is(oldScore - 2));
                break;
            } catch (AssertionError assertionError) {
                ScoreTracker newScore = new ScoreTracker(activity.getApplicationContext());
                assertThat(newScore.getScore(Game.HANGMAN), is(oldScore - 3));
                oldScore = newScore.getScore(Game.HANGMAN);
            }
        }
        Espresso.pressBackUnconditionally();
        onView(withId(R.id.hangmanStartGameButton)).check(matches(isDisplayed()));
    }

    @Test
    public void addCustomWord() {

        // Go from Main Menu to Hangman Menu
        onView(withId(R.id.hangmanButton)).perform(click());

        onView(withId(R.id.hangmanRemoveWordTextInput)).perform(typeText("hello"));
        onView(withId(R.id.hangmanRemoveWordButton)).perform(click());

        onView(withId(R.id.hangmanAddWordTextInput)).perform(typeText("hello"));
        onView(withId(R.id.hangmanAddWordButton)).perform(click());


        onView(withId(R.id.hangmanRemoveWordTextInput)).perform(typeText("hello"));
        onView(withId(R.id.hangmanRemoveWordButton)).perform(click());


    }


    @Test
    public void doubledeleteCustomWord() {

        // Go from Main Menu to Hangman Menu
        onView(withId(R.id.hangmanButton)).perform(click());

        onView(withId(R.id.hangmanRemoveWordTextInput)).perform(typeText("hello"));
        onView(withId(R.id.hangmanRemoveWordButton)).perform(click());

        onView(withId(R.id.hangmanAddWordTextInput)).perform(typeText("hello"));
        onView(withId(R.id.hangmanAddWordButton)).perform(click());


        onView(withId(R.id.hangmanRemoveWordTextInput)).perform(typeText("hello"));
        onView(withId(R.id.hangmanRemoveWordButton)).perform(click());


        onView(withId(R.id.hangmanRemoveWordTextInput)).perform(typeText("hello"));
        onView(withId(R.id.hangmanRemoveWordButton)).perform(click());

    }

    @Test
    public void doubleAddCustomWord() {

        // Go from Main Menu to Hangman Menu
        onView(withId(R.id.hangmanButton)).perform(click());

        onView(withId(R.id.hangmanRemoveWordTextInput)).perform(typeText("hello"));
        onView(withId(R.id.hangmanRemoveWordButton)).perform(click());

        onView(withId(R.id.hangmanAddWordTextInput)).perform(typeText("hello"));
        onView(withId(R.id.hangmanAddWordButton)).perform(click());


        onView(withId(R.id.hangmanAddWordTextInput)).perform(typeText("hello"));
        onView(withId(R.id.hangmanAddWordButton)).perform(click());


        onView(withId(R.id.hangmanRemoveWordTextInput)).perform(typeText("hello"));
        onView(withId(R.id.hangmanRemoveWordButton)).perform(click());


    }

    @Test
    public void scoreReduced() {
        Activity activity = activityRule.getActivity();
        ScoreTracker st = new ScoreTracker(activity.getApplicationContext());
        int oldScore;
        oldScore = st.getScore(Game.HANGMAN);
        // Go from Main Menu to Hangman Menu
        onView(withId(R.id.hangmanButton)).perform(click());

        // Go from Hangman Menu to Game
        onView(withId(R.id.hangmanStartGameButton)).perform(click());
        String letter = ".";
        for (int i = 0; i <= 12; i++) {
            onView(withId(R.id.plainText_nextChar)).perform(typeText(letter));
            try {
                onView(withId(R.id.button_playagain)).check(matches(isDisplayed()));
                onView(withId(R.id.button_playagain)).perform(click());
                onView(isRoot()).perform(pressBack());
                onView(isRoot()).perform(pressBack());
                // View displayed
                break;
            } catch (AssertionError e) {
                // View not displayed
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // View not displayed
        }
        ScoreTracker st2 = new ScoreTracker(activity.getApplicationContext());
        assertThat(st2.getScore(Game.HANGMAN), is(oldScore - 2));
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

