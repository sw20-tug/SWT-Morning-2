package com.swt20.swt_morning2;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TicTacToeGameLogicTest {

    @Mock
    private Context context;

    @Mock
    private SharedPreferences mockSharedPreferences;
    @Mock
    private SharedPreferences.Editor sharedprefeditor;

    @Before
    public void setup() {
        when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(mockSharedPreferences);
        when(mockSharedPreferences.getInt(eq("TICTACTOE_SCORE"), anyInt())).thenReturn(5);
        when(mockSharedPreferences.edit()).thenReturn(sharedprefeditor);
    }

    @Test
    public void testDoubleTick() {
        TicTacToeGameLogic logic = new TicTacToeGameLogic(0, 1);
        Assert.assertTrue(logic.turn(0, 0));
        Assert.assertTrue(logic.turn(1, 0));
        Assert.assertFalse(logic.turn(0, 0));
    }

    @Test
    public void testTickAll() {
        TicTacToeGameLogic logic = new TicTacToeGameLogic(0, 1);
        Assert.assertTrue(logic.turn(0, 0));
        Assert.assertTrue(logic.turn(0, 1));
        Assert.assertTrue(logic.turn(0, 2));
        Assert.assertTrue(logic.turn(1, 0));
        Assert.assertTrue(logic.turn(1, 1));
        Assert.assertTrue(logic.turn(1, 2));
        Assert.assertTrue(logic.turn(2, 0));
        Assert.assertTrue(logic.turn(2, 1));
        Assert.assertTrue(logic.turn(2, 2));
    }

    @Test
    public void checkHorizontalWinner() {
        TicTacToeGameLogic logic = new TicTacToeGameLogic(0, 1);
        Assert.assertTrue(logic.turn(0,0));
        Assert.assertTrue(logic.turn(0,1));
        Assert.assertTrue(logic.turn(1,0));
        Assert.assertTrue(logic.turn(1,1));
        Assert.assertTrue(logic.turn(2,0));
        Assert.assertNotNull(logic.getWinner());
        scoreChangedBy(logic, 1);
    }

    @Test
    public void checkVerticalWinner() {
        TicTacToeGameLogic logic = new TicTacToeGameLogic(0, 1);
        Assert.assertTrue(logic.turn(0,0));
        Assert.assertTrue(logic.turn(1,0));
        Assert.assertTrue(logic.turn(0,1));
        Assert.assertTrue(logic.turn(1,1));
        Assert.assertTrue(logic.turn(0,2));
        Assert.assertNotNull(logic.getWinner());
        scoreChangedBy(logic, 1);
    }

    @Test
    public void checkCrossWinner() {
        TicTacToeGameLogic logic = new TicTacToeGameLogic(0, 1);
        Assert.assertTrue(logic.turn(0,0));
        Assert.assertTrue(logic.turn(1,0));
        Assert.assertTrue(logic.turn(1,1));
        Assert.assertTrue(logic.turn(1,2));
        Assert.assertTrue(logic.turn(2,2));
        Assert.assertNotNull(logic.getWinner());
        scoreChangedBy(logic, 1);
    }

    @Test
    public void checkSecondCrossWinner() {
        TicTacToeGameLogic logic = new TicTacToeGameLogic(0, 1);
        Assert.assertTrue(logic.turn(2,0));
        Assert.assertTrue(logic.turn(1,0));
        Assert.assertTrue(logic.turn(1,1));
        Assert.assertTrue(logic.turn(1,2));
        Assert.assertTrue(logic.turn(0,2));
        Assert.assertNotNull(logic.getWinner());
        scoreChangedBy(logic, 1);
    }

    @Test
    public void checkVerticalLose() {
        TicTacToeGameLogic logic = new TicTacToeGameLogic(0, 1);
        Assert.assertTrue(logic.turn(1,0));
        Assert.assertTrue(logic.turn(0,0));
        Assert.assertTrue(logic.turn(1,1));
        Assert.assertTrue(logic.turn(0,1));
        Assert.assertTrue(logic.turn(2,2));
        Assert.assertTrue(logic.turn(0,2));
        Assert.assertNotNull(logic.getWinner());
        scoreChangedBy(logic, -2);
    }


    @Test
    public void checkSimpleNoWinner() {
        TicTacToeGameLogic logic = new TicTacToeGameLogic(0, 1);
        Assert.assertTrue(logic.turn(0,0));
        Assert.assertNull(logic.getWinner());
        scoreChangedBy(logic, 0);
    }

    @Test
    public void checkNoWinnerSemiFullBoard() {
        TicTacToeGameLogic logic = new TicTacToeGameLogic(0, 1);
        Assert.assertTrue(logic.turn(2,0));
        Assert.assertTrue(logic.turn(1,0));
        Assert.assertTrue(logic.turn(1,1));
        Assert.assertTrue(logic.turn(1,2));
        Assert.assertNull(logic.getWinner());
        scoreChangedBy(logic, 0);
    }


    @Test
    public void checkEmptyBoardWinner() {
        TicTacToeGameLogic logic = new TicTacToeGameLogic(0, 1);
        Assert.assertNull(logic.getWinner());
        scoreChangedBy(logic, 0);
    }

    @Test
    public void checkFullBoardNoWinner() {
        TicTacToeGameLogic logic = new TicTacToeGameLogic(0, 1);
        Assert.assertTrue(logic.turn(0,0));
        Assert.assertTrue(logic.turn(1,0));
        Assert.assertTrue(logic.turn(1,1));
        Assert.assertTrue(logic.turn(2,2));
        Assert.assertTrue(logic.turn(0,2));
        Assert.assertTrue(logic.turn(0,1));
        Assert.assertTrue(logic.turn(1,2));
        Assert.assertTrue(logic.turn(2,0));
        Assert.assertTrue(logic.turn(2,1));
        Assert.assertNull(logic.getWinner());
        scoreChangedBy(logic, 0);
    }

    private void scoreChangedBy(TicTacToeGameLogic logic, Integer change) {
        ScoreTracker st = new ScoreTracker(context);
        st.setScore(Game.TICTACTOE, 42);
        logic.changeScore(logic.getWinner(), st);
        verify(sharedprefeditor).putInt(eq("TICTACTOE_SCORE"), eq(42 + change));
    }

}