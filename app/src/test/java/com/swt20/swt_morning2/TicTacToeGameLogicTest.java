package com.swt20.swt_morning2;


import android.content.Context;
import android.content.SharedPreferences;

import org.checkerframework.dataflow.qual.TerminatesExecution;
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
public class TicTacToeGameLogicTest {
    @Mock
    private Context context;

    @Mock
    private SharedPreferences mockSharedPreferences;
    @Mock
    private SharedPreferences.Editor sharedprefeditor;


    @Before
    public void setup() {

    }

    @Test
    public void testDoubleTick(){
        TicTacToeGameLogic logic = new TicTacToeGameLogic(0, 1);
        Assert.assertTrue(logic.turn(0,0));
        Assert.assertTrue(logic.turn(1,0));
        Assert.assertFalse(logic.turn(0,0));
    }

    @Test
    public void testTickAll(){
        TicTacToeGameLogic logic = new TicTacToeGameLogic(0, 1);
        Assert.assertTrue(logic.turn(0,0));
        Assert.assertTrue(logic.turn(0,1));
        Assert.assertTrue(logic.turn(0,2));
        Assert.assertTrue(logic.turn(1,0));
        Assert.assertTrue(logic.turn(1,1));
        Assert.assertTrue(logic.turn(1,2));
        Assert.assertTrue(logic.turn(2,0));
        Assert.assertTrue(logic.turn(2,1));
        Assert.assertTrue(logic.turn(2,2));

    }
}