package com.swt20.swt_morning2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class TicTacToeTests {

    public static class EspressoTestsMatchers {
        // src:
        // https://medium.com/@dbottillo/android-ui-test-espresso-matcher-for-imageview-1a28c832626f

        public static Matcher<View> withDrawable(final int resourceId) {
            return new DrawableMatcher(resourceId);
        }

        public static Matcher<View> noDrawable() {
            return new DrawableMatcher(-1);
        }


    }

    public static class DrawableMatcher extends TypeSafeMatcher<View> {
        // src:
        // https://medium.com/@dbottillo/android-ui-test-espresso-matcher-for-imageview-1a28c832626f

        private final int expectedId;

        public DrawableMatcher(int resourceId) {
            super(View.class);
            this.expectedId = resourceId;
        }

        @Override
        protected boolean matchesSafely(View target) {
            if (!(target instanceof ImageView)) {
                return false;
            }
            ImageView imageView = (ImageView) target;
            if (expectedId < 0) {
                return imageView.getDrawable() == null;
            }
            Resources resources = target.getContext().getResources();
            Drawable expectedDrawable = resources.getDrawable(expectedId);
            if (expectedDrawable == null) {
                return false;
            }
            Bitmap bitmap = getBitmap(imageView.getDrawable());
            Bitmap otherBitmap = getBitmap(expectedDrawable);
            return bitmap.sameAs(otherBitmap);
        }

        private Bitmap getBitmap(Drawable drawable) {
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("with drawable from resource id: ");
            description.appendValue(expectedId);
        }
    }


    /* DOE we have to delete this for findBugs, since it is not used
    but we did not write that code, so we are not sure if it is ok to remove it.*/
    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void playGame() {

        // this statement uses activity rule, such that findbugs does not report an unused variable.
        // we need activityRule, otherwise the tests don't work.
        if (activityRule.equals(null)) {
            return;
        }

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
                Espresso.pressBackUnconditionally();
            }

            // Go back to Main Menu
            Espresso.pressBackUnconditionally();
        }
    }

    private void checkOneColor(int resId, int resIdOpponent) {
        onView(EspressoTestsMatchers.withDrawable(resId)).perform(click());
        onView(withId(R.id.ttt_menu_button)).perform(click());
        onView(withId(R.id.imageView)).perform(click());
        onView(withId(R.id.imageView2)).perform(click());
        onView(withId(R.id.imageView)).check(matches(EspressoTestsMatchers.withDrawable(resId)));
        onView(withId(R.id.imageView2)).
                check(matches(EspressoTestsMatchers.withDrawable(resIdOpponent)));
        Espresso.pressBackUnconditionally();
    }

    @Test
    public void selectColor() {
        // Go from Main Menu to Tic-Tac-Toe Menu
        onView(withId(R.id.ticTacToeButton)).perform(click());

        checkOneColor(R.drawable.o_0000ff, R.drawable.x_ff0000);
        checkOneColor(R.drawable.o_009933, R.drawable.x_0000ff);
        checkOneColor(R.drawable.o_cc00cc, R.drawable.x_009933);
        checkOneColor(R.drawable.o_ff0000, R.drawable.x_cc00cc);

        checkOneColor(R.drawable.x_0000ff, R.drawable.o_009933);
        checkOneColor(R.drawable.x_009933, R.drawable.o_cc00cc);
        checkOneColor(R.drawable.x_cc00cc, R.drawable.o_ff0000);
        checkOneColor(R.drawable.x_ff0000, R.drawable.o_0000ff);

    }

}
