package com.swt20.swt_morning2;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

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
public class WhiteTilesTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    private List<ViewInteraction> buttons = new ArrayList<>();

    @Before
    public void setupButtons(){
        buttons.clear();
        WhiteTilesGameLogic.tileButtonIds.forEach(integer -> {
            buttons.add(onView(withId(integer)));
        });
    }


    @Test
    public void clickOnWhiteButton(){
        onView(withId(R.id.whiteTilesButton)).perform(click());

        onView(withId(R.id.tiles_menu_button)).perform(click());
        View whiteButton = getButtonWithColor(Color.WHITE);
        onView(withId(whiteButton.getId())).perform(click());
        onView(withText(R.string.you_lose)).inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnBlackButton(){
        onView(withId(R.id.whiteTilesButton)).perform(click());

        onView(withId(R.id.tiles_menu_button)).perform(click());
        View whiteButton = getButtonWithColor(Color.BLACK);
        ScoreTracker st = new ScoreTracker(activityRule.getActivity().getApplicationContext());
        int oldScore = st.getScore(Game.TILES);
        onView(withId(whiteButton.getId())).perform(click());
        Assert.assertEquals(oldScore + 1, st.getScore(Game.TILES));
    }

    public View getButtonWithColor(Integer color) {
        View button = null;
        for (Integer integer : WhiteTilesGameLogic.tileButtonIds) {
            View possibleButton = activityRule.getActivity().findViewById(integer);
            Drawable background = possibleButton.getBackground();
            ColorDrawable backgroundDrawable = (ColorDrawable) background;
            if(backgroundDrawable.getColor() == color) {
                button = possibleButton;
                break;
            }
        }
        return button;
    }
}
