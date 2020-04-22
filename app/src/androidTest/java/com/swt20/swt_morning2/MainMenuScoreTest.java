package com.swt20.swt_morning2;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class MainMenuScoreTest {

    @Before
    public void intentWithStubbedNoteId() {
        Intent startIntent = new Intent();
        activityRule.launchActivity(startIntent);
    }

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<MainActivity>(MainActivity.class, true, false) {

        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            ScoreTracker st = new ScoreTracker(InstrumentationRegistry.getInstrumentation().getTargetContext());
            st.setScore(Game.TICTACTOE, 0);
            st.setScore(Game.HANGMAN, 0);
            st.setScore(Game.TILES, 0);
        }
    };

    @Test
    public void TestTicTacToeScore() {
        onView(withId(R.id.ticTacToeScore)).check(matches(withText("Score: 0")));
    }

    @Test
    public void HangmanScore() {
        onView(withId(R.id.hangmanScore)).check(matches(withText("Score: 0")));
    }

    @Test
    public void TilesScore() {
        onView(withId(R.id.whiteTilesScore)).check(matches(withText("Score: 0")));
    }

}


