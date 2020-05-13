package com.swt20.swt_morning2;


import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class WhiteTilesTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    private List<ViewInteraction> buttons;

    @Before
    public void setupButtons(){
        List<ViewInteraction> buttons = new ArrayList<>();
        buttons.add(onView(withId(R.id.tilesFieldButton0)));
        buttons.add(onView(withId(R.id.tilesFieldButton1)));
        buttons.add(onView(withId(R.id.tilesFieldButton2)));
        buttons.add(onView(withId(R.id.tilesFieldButton3)));
        buttons.add(onView(withId(R.id.tilesFieldButton4)));
        buttons.add(onView(withId(R.id.tilesFieldButton5)));
        buttons.add(onView(withId(R.id.tilesFieldButton6)));
        buttons.add(onView(withId(R.id.tilesFieldButton7)));
        buttons.add(onView(withId(R.id.tilesFieldButton8)));
        buttons.add(onView(withId(R.id.tilesFieldButton9)));
        buttons.add(onView(withId(R.id.tilesFieldButton10)));
        buttons.add(onView(withId(R.id.tilesFieldButton11)));
        buttons.add(onView(withId(R.id.tilesFieldButton12)));
        buttons.add(onView(withId(R.id.tilesFieldButton13)));
        buttons.add(onView(withId(R.id.tilesFieldButton14)));
        buttons.add(onView(withId(R.id.tilesFieldButton15)));
        this.buttons = buttons;
    }


    @Test
    public void visitGameAndTouchRandomButtons(){
        onView(withId(R.id.whiteTilesButton)).perform(click());

        onView(withId(R.id.tiles_menu_button)).perform(click());
        for (ViewInteraction button : buttons) {
            button.perform(click());
        }

    }
}
