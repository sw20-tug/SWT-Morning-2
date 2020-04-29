package com.swt20.swt_morning2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;




@RunWith(MockitoJUnitRunner.class)
public class TicTacToeGameLogicTest {

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