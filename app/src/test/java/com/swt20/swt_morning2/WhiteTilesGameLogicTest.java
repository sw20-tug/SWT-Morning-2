package com.swt20.swt_morning2;

import android.graphics.Color;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WhiteTilesGameLogicTest {

    @Test
    public void testRandomPlacement() {
        WhiteTilesGameLogic logic = new WhiteTilesGameLogic();
        Assert.assertEquals(0, logic.getTilesButtons().size());
        logic.scrambleButtons();
        Assert.assertEquals(16, logic.getTilesButtons().size());
        logic.getTilesButtons().forEach((buttonId, colorCode) -> {
            Assert.assertTrue(colorCode == Color.BLACK || colorCode == Color.WHITE);
        });
        Assert.assertTrue(logic.getTilesButtons().containsValue(Color.BLACK));

    }

    @Test
    public void testBlackTileGuarantee() {
        WhiteTilesGameLogic logic = new WhiteTilesGameLogic(() -> 1);
        logic.scrambleButtons();
        Assert.assertTrue(logic.getTilesButtons().containsValue(Color.BLACK));

    }
}
