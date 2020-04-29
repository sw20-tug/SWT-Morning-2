package com.swt20.swt_morning2;


import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class ScoreTrackerTest {
    @Mock
    private Context context;

    @Mock
    private SharedPreferences mockSharedPreferences;
    @Mock
    private SharedPreferences.Editor sharedprefeditor;


    @Before
    public void setup() {
        when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(mockSharedPreferences);
        when(mockSharedPreferences.getInt(eq("HANGMAN_SCORE"), anyInt())).thenReturn(5);
        when(mockSharedPreferences.edit()).thenReturn(sharedprefeditor);
    }

    @Test(expected = Exception.class)
    public void notSetPref() {
        new ScoreTracker(null);
    }

    @Test
    public void testHangmanScoreRetrival() {
        ScoreTracker st = new ScoreTracker(context);
        Assert.assertEquals(st.getScore(Game.HANGMAN), 5);
    }

    @Test
    public void testTilesScoreRetrival() {
        ScoreTracker st = new ScoreTracker(context);
        Assert.assertEquals(st.getScore(Game.TILES), 0);
    }

    @Test
    public void testStoreScore() {
        ScoreTracker st = new ScoreTracker(context);
        st.setScore(Game.TICTACTOE, 42);
        verify(mockSharedPreferences).edit();
        verify(sharedprefeditor).putInt(eq("TICTACTOE_SCORE"), eq(42));
        verify(sharedprefeditor).putInt(eq("HANGMAN_SCORE"), eq(5));
        verify(sharedprefeditor).putInt(eq("TILES_SCORE"), eq(0));
        verify(sharedprefeditor).apply();
    }

    @Test
    public void testaddScore() {
        ScoreTracker st = new ScoreTracker(context);
        st.addScore(Game.TICTACTOE, 56);
        verify(mockSharedPreferences).edit();
        verify(sharedprefeditor).putInt(eq("TICTACTOE_SCORE"), eq(56));
        verify(sharedprefeditor).putInt(eq("HANGMAN_SCORE"), eq(5));
        verify(sharedprefeditor).putInt(eq("TILES_SCORE"), eq(0));
        verify(sharedprefeditor).apply();
    }

    @Test
    public void testreduceScore() {
        ScoreTracker st = new ScoreTracker(context);
        st.reduceScore(Game.HANGMAN, 3);
        verify(mockSharedPreferences).edit();
        verify(sharedprefeditor).putInt(eq("TICTACTOE_SCORE"), eq(0));
        verify(sharedprefeditor).putInt(eq("HANGMAN_SCORE"), eq(2));
        verify(sharedprefeditor).putInt(eq("TILES_SCORE"), eq(0));
        verify(sharedprefeditor).apply();
    }

    @Test
    public void testaddNegativeScore() {
        ScoreTracker st = new ScoreTracker(context);
        st.addScore(Game.TICTACTOE, -56);
        verify(mockSharedPreferences).edit();
        verify(sharedprefeditor).putInt(eq("TICTACTOE_SCORE"), eq(-56));
        verify(sharedprefeditor).putInt(eq("HANGMAN_SCORE"), eq(5));
        verify(sharedprefeditor).putInt(eq("TILES_SCORE"), eq(0));
        verify(sharedprefeditor).apply();
    }
}